package jp.co.reggie.oldeal.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.reggie.oldeal.entity.User;

/**
 * @author Administrator
 */
public interface UserService extends IService<User> {

	/**
	 * 用戸登錄
	 *
	 * @param map     用戸信息集合
	 * @param session 本次會話
	 * @return User 用戸實體類
	 */
	User login(Map<Object, String> map, HttpSession session);

	/**
	 * 用戸登錄認證訊息發送
	 *
	 * @param user    用戸實體類
	 * @param session 本次會話
	 */
	void sendMessage(User user, HttpSession session);
}
