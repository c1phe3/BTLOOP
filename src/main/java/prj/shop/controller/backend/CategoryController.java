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

import prj.shop.model.Category;
import prj.shop.service.CategoryService;
import prj.shop.service.UserService;
import prj.shop.model.User;





@Controller
@RequestMapping(value = "/admin/category/")
public class CategoryController {
	@Autowired
	private CategoryService categoryService; 
	@Autowired
	private UserService userService;

//	Xem	
		@RequestMapping(value = "list", method = RequestMethod.GET)
		public String listCategory(final Model model) {
			
//		List<Category> categories =  categoryService.findAll();
			List<Category> categories =  categoryService.findAllActive();
		
			model.addAttribute("categories", categories);
			return "backend/category-list";
		}
//	Thêm	
		@RequestMapping(value = "add", method = RequestMethod.GET)
		public String addCategory(final Model model) {
			
			Category category =  new Category();
			model.addAttribute("category", category);
			
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			return "backend/category-add";
		}
		
		@RequestMapping(value = "add-save", method = RequestMethod.POST)
		public String addSave(final Model model,
				@ModelAttribute("category") Category category) {
			
			categoryService.saveOrUpdate(category);
			
			return "redirect:/admin/category/add";
		}
		
		@RequestMapping(value = "edit/{categoryId}", method = RequestMethod.GET)
		public String editCategory(final Model model,
				@PathVariable("categoryId") int categoryId) {
			
			//Lấy category trong db
			Category category =  categoryService.getById(categoryId);
			category.setUpdateDate(new Date());
			model.addAttribute("category", category);
			
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			
			return "backend/category-edit";
		}
		
		@RequestMapping(value = "edit-save", method = RequestMethod.POST)
		public String editSave(final Model model,
				@ModelAttribute("category") Category category) {
			
			categoryService.saveOrUpdate(category);
			
			return "redirect:/admin/category/list";
		}
//		Xóa 
//		1.Xóa hẳn trong db
//		@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
//		public String deleteCategory(final Model model,
//				@PathVariable("categoryId") int categoryId) {
//			
//			categoryService.deleteCategoryById(categoryId);
//			
//			return "redirect:/admin/category/list";
//		}
//		2.Inactive
		@RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
		public String deleteCategory(final Model model,
				@PathVariable("categoryId") int categoryId) {
			
			Category category = categoryService.getById(categoryId);
			category.setStatus(false);
			categoryService.saveOrUpdate(category);
			
			return "redirect:/admin/category/list";
		}
}
