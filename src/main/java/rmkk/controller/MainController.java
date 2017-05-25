package rmkk.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import rmkk.entity.User;
import rmkk.service.IUserService;

@Controller
@RequestMapping("/")
public class MainController
{
	@Autowired
	private IUserService userService;
	@RequestMapping("login")
	public String goLogin(){
		return "welcome";
	}
	@RequestMapping("into")
	public String doLogin(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
		String usernameString=request.getParameter("username");
		String passwordString=request.getParameter("password");
		User user=new User();
		user.setUserid(usernameString);
		user.setPassword(passwordString);
		user=userService.getUserByUser(user);
		if(null!=user){
			session.setAttribute("user", user);
			return "login";
		}else{
			request.setAttribute("msg", "用户名或密码错误");
			try
			{
				request.getRequestDispatcher("login").forward(request, response);
			} catch (ServletException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
