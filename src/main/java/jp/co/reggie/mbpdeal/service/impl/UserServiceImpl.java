package jp.co.reggie.mbpdeal.service.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.reggie.mbpdeal.common.CustomException;
import jp.co.reggie.mbpdeal.common.CustomMessages;
import jp.co.reggie.mbpdeal.entity.User;
import jp.co.reggie.mbpdeal.mapper.UserMapper;
import jp.co.reggie.mbpdeal.service.UserService;
import jp.co.reggie.mbpdeal.utils.SMSUtils;
import jp.co.reggie.mbpdeal.utils.StringUtils;
import jp.co.reggie.mbpdeal.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2022-11-29
 */
@Slf4j
@Service
@Transactional(rollbackFor = PSQLException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	/**
	 * 用戸登錄
	 *
	 * @param map     用戸信息集合
	 * @param session 本次會話
	 * @return User 用戸實體類
	 */
	@Override
	public User login(final Map<Object, String> map, final HttpSession session) {
		// 獲取手機號；
		final String phoneNo = map.get("phoneNo");
		// 獲取驗證碼；
		final String code = map.get("code");
		// 獲取Session中保存的驗證碼；
		final String codeInSession = session.getAttribute(phoneNo).toString();
		// 進行驗證碼的比對；
		if (StringUtils.isNotEqual(code, codeInSession) || StringUtils.isEmpty(codeInSession)) {
			throw new CustomException(CustomMessages.ERP014);
		}
		// 認證成功，放行登錄並驗證是否為新注冊手機號；
		final LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getPhoneNo, phoneNo);
		User user = this.getOne(queryWrapper);
		// 如果是新用戸則自動完成注冊；
		if (user == null) {
			user = new User();
			user.setPhoneNo(phoneNo);
			user.setStatus(1);
			this.save(user);
		}
		return user;
	}

	/**
	 * 用戸登錄認證訊息發送
	 *
	 * @param user    用戸實體類
	 * @param session 本次會話
	 */
	@Override
	public void sendMessage(final User user, final HttpSession session) {
		// 獲取手機號；
		final String phoneNo = user.getPhoneNo();
		if (phoneNo.isBlank()) {
			throw new CustomException(CustomMessages.ERP013);
		}
		// 生成隨機的6位數驗證碼；
		final String code = ValidateCodeUtils.generateValidateCode(6).toString();
		// 將生成的驗證碼保存到Session中；
		session.setAttribute(phoneNo, code);
		log.info("本次的驗證碼為：{}", code);
		// 調用阿里雲提供的訊息服務API完成送訊；
		SMSUtils.sendMessage("瑞吉外賣", "00024", phoneNo, code);
	}
}
