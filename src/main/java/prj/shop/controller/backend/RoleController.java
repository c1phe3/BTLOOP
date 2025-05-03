package prj.shop.controller.backend;

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

import prj.shop.controller.BaseController;
import prj.shop.model.Role;
import prj.shop.model.User;
import prj.shop.model.UserRole;
import prj.shop.service.RoleService;
import prj.shop.service.UserRoleService;
import prj.shop.service.UserService;

@Controller
@RequestMapping(value = "/admin/role/")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listRole(final Model model) {

		List<Role> roles = roleService.findAll();

		model.addAttribute("roles", roles);
		return "backend/role-list";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addRole(final Model model) {

		Role role = new Role();
		model.addAttribute("role", role);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "backend/role-add";
	}
	
	
	
	//check
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editRole(final Model model) {

		List<Role> roles = roleService.findAll();
		model.addAttribute("role", roles);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "backend/role-edit-change";
	}
	@RequestMapping(value = "assign", method = RequestMethod.POST)
	public String assignRole(@RequestParam("userId") int userId, @RequestParam("roleId")int roleId) {
		userRoleService.updateRole(userId, roleId);
		// User user = userService.getById(userId);
	    // Role role = roleService.getById(roleId);
	     //UserRole userRole = new UserRole(); // Tạo một đối tượng UserRole mới
	     //userRole.setUser(userId); // Gán user cho UserRole
	     //userRole.setRole(roleId); // Gán role cho UserRole
	     //userRoleService.saveOrUpdate(userRole);
	     //user.getRoles().add(role); // Thêm role vào danh sách roles của user
	    // userService.saveOrUpdate(user);
		return "redirect:/admin/role/list";
	}
	
	
	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(final Model model, @ModelAttribute("role") Role role) {

		roleService.saveOrUpdate(role);

		return "redirect:/admin/role/add";
	}

	@RequestMapping(value = "edit/{roleId}", method = RequestMethod.GET)
	public String editRole(final Model model, @PathVariable("roleId") int roleId) {

		// Lấy role trong db
		Role role = roleService.getById(roleId);
		role.setUpdateDate(new Date());
		model.addAttribute("role", role);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		return "backend/role-edit";
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, @ModelAttribute("role") Role role) {

		roleService.saveOrUpdate(role);

		return "redirect:/admin/role/list";
	}

	// Xóa
	// 1.Xóa hẳn trong db
//	@RequestMapping(value = "delete/{roleId}", method = RequestMethod.GET)
//	public String deleteRole(final Model model,
//			@PathVariable("roleId") int roleId) {
//		
//		roleService.deleteRoleById(roleId);
//		
//		return "redirect:/admin/role/list";
//	}
	// 2.Inactive
	@RequestMapping(value = "delete/{roleId}", method = RequestMethod.GET)
	public String deleteRole(final Model model, @PathVariable("roleId") int roleId) {

		Role role = roleService.getById(roleId);
		role.setStatus(false);
		roleService.saveOrUpdate(role);

		return "redirect:/admin/role/list";
	}
}
