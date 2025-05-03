package prj.shop.controller.backend;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import prj.shop.controller.BaseController;
import prj.shop.model.Role;
import prj.shop.model.User;
import prj.shop.service.BaseService;
import prj.shop.service.RoleService;
import prj.shop.service.UserService;


@Controller
@RequestMapping(value = "/admin/user/")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listUser(final Model model) {
		List<User> users = userService.findAllActive();
		
		model.addAttribute("users", users);
		return "backend/user-list";
	}
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addUser(final Model model) {
		
	    User user =  new User();
		model.addAttribute("user", user);
		
		List<User> users = userService.findAllActive();
		model.addAttribute("users", users);
		
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "backend/user-add";
	}
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(final Model model,
			@ModelAttribute("user") User user,
			@RequestParam("avatarFile") MultipartFile avatarFile)throws IOException {
		
		userService.saveAddUser(user, avatarFile);
		
		return "redirect:/admin/user/add";
	}
	@RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
	public String editUser(final Model model,
			@PathVariable("userId") int userId) {
		
		//Lấy user trong db
		User user =  userService.getById(userId);
		user.setUpdateDate(new Date());
		model.addAttribute("user", user);
		
		List<User> users = userService.findAllActive();
		model.addAttribute("users", users);
		
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		return "backend/user-edit";
	}
	
	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model,
			@ModelAttribute("user") User user) {
		
		userService.saveOrUpdate(user);
		
		return "redirect:/admin/user/list";
	}
	//Xóa 
		//1.Xóa hẳn trong db
		@RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
		public String deleteUser(final Model model,
				@PathVariable("userId") int userId) {
			
			//User user = userService.getById(userId);
			userService.deleteById(userId);
			
			return "redirect:/admin/user/list";
		}
		//2.Inactive
//		@RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
//		public String deleteUser(final Model model,
//				@PathVariable("userId") int userId) {
//			
//			User user = userService.getById(userId);
//			user.setStatus(false);
//			userService.saveOrUpdate(user);
//			
//			return "redirect:/admin/user/list";
//		}
	
}


