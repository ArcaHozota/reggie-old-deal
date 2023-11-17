package jp.co.reggie.mbpdeal.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.reggie.mbpdeal.common.CommonMessages;
import jp.co.reggie.mbpdeal.entity.User;
import jp.co.reggie.mbpdeal.service.UserService;
import jp.co.reggie.mbpdeal.utils.Reggie;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用戸登錄
	 *
	 * @param user    用戸實體類
	 * @param session 本次會話
	 * @return R.success(登錄成功的信息)
	 */
	@PostMapping("/login")
	public Reggie<User> login(@RequestBody final Map<Object, String> userMap, final HttpSession session) {
		final User user = this.userService.login(userMap, session);
		return Reggie.success(user);
	}

	/**
	 * 用戸登錄認證訊息發送
	 *
	 * @param user    用戸實體類
	 * @param session 本次會話
	 * @return R.success(訊息發送成功的信息)
	 */
	@PostMapping("/sendMsg")
	public Reggie<String> sendMsg(@RequestBody final User user, final HttpSession session) {
		this.userService.sendMessage(user, session);
		return Reggie.success(CommonMessages.SRP015);
	}
}
