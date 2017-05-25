package rmkk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rmkk.entity.User;
import rmkk.service.IUserService;

@Controller
@RequestMapping("user/")
public class UserController
{
	@Autowired
	private IUserService userService;
	@RequestMapping(value="register",method = RequestMethod.POST)
	public String doRegistered(User user,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		if(userService.addUser(user)){
			session.setAttribute("user", user);
			return "login";
		}else {
			return "welcome";
		}
	}
	
	@ResponseBody
	@RequestMapping("checknew")
	public boolean checkNewUser(User user){
		if(userService.getUserByName(user.getUserid())==0){
			return true;
		}
		else {
			return false;
		}
	}
}
