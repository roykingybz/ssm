package com.gucheng.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gucheng.ssm.model.User;
import com.gucheng.ssm.service.UserService;

@RequestMapping("/user")
@Controller
public class LoginController {

	@Resource
	private UserService userService;

	/**
	 * 访问登录页面
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	/**
	 * 用户登录
	 * 
	 * @param userName  用户名
	 * @param passWord  密码
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String showLogin(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "passWord", defaultValue = "") String passWord,HttpSession session) {
		User user = userService.findUser(id, passWord);
		if (user == null) {
			return "redirect:/user/login.do";
		} else {
			session.setAttribute("userid", user.getId());
			session.setAttribute("login_status", true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setLasttime(sdf.format(new Date()));
			userService.update(user);
			return "index";
		}

	}

	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userid");
		session.removeAttribute("login_status");
		return "login";
	}

}
