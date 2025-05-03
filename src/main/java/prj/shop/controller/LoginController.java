
package prj.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prj.shop.model.Role;
import prj.shop.model.User;
import prj.shop.service.RoleService;
import prj.shop.service.UserService;


@Controller
public class LoginController extends BaseController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "/signup";
	}
	
	@Autowired RoleService roleService;
	
	@Autowired UserService userService;
	
	//@RequestMapping(value = "/login_processing_url",method = RequestMethod.POST)
	//public ()
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(final HttpServletRequest request) {
	    User user = new User();
	    user.setUsername(request.getParameter("username"));
	    user.setPassword(request.getParameter("password"));
	    user.setEmail(request.getParameter("email"));
	    user.setMobile(request.getParameter("mobile"));
	    // Gán vai trò cho người dùng là "User"
	    Role role = roleService.getRoleByName("User"); 
	    user.addRelationalUserRole(role);
	    userService.saveOrUpdate(user);
	    return "redirect:/login";
	}
}
