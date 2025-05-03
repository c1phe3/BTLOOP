package prj.shop.controller.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import prj.shop.controller.BaseController;
import prj.shop.dto.Constants;
import prj.shop.dto.SearchModel;
import prj.shop.model.Category;
import prj.shop.model.Product;
import prj.shop.model.User;
import prj.shop.service.CategoryService;
import prj.shop.service.ProductService;
import prj.shop.service.UserService;



@Controller
@RequestMapping(value = "/admin/product/")
public class ProductController extends BaseController implements Constants {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;

//	@RequestMapping(value = "list", method = RequestMethod.GET)
//	public String listProduct(final Model model) {
//		
//		List<Product> products = productService.findAllActive();
//		model.addAttribute("products", products);
//		return "backend/product-list";
//	}
	// Search product
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String searchProduct(final Model model, final HttpServletRequest request) {
		SearchModel productSearch = new SearchModel();
		// Tim theo status
		productSearch.setStatus(2);
		if (!StringUtils.isEmpty(request.getParameter("status"))) {
			productSearch.setStatus(Integer.parseInt(request.getParameter("status")));
		}

		// Tim theo category
		productSearch.setCategoryId(0);// all
		if (!StringUtils.isEmpty(request.getParameter("categoryId"))) { // Co chon category
			productSearch.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		}
		// Tìm theo keyword
		productSearch.setKeyword(null);
		String keyword = request.getParameter("keyword");
		if (keyword != null || !StringUtils.isEmpty(keyword)) {
			productSearch.setKeyword(keyword);
		}
		// Tìm theo date to date
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		productSearch.setBeginDate(null);
		productSearch.setEndDate(null);
		if (beginDate != null && !StringUtils.isEmpty(beginDate) && endDate != null && !StringUtils.isEmpty(endDate)) {
			productSearch.setBeginDate(beginDate);
			productSearch.setEndDate(endDate);
		}

		//Phân trang
				final int SIZE_OF_PAGE = 10;
				productSearch.setCurrentPage(1);//Mới truy cập luôn là trang 1
				if (!StringUtils.isEmpty(request.getParameter("currentPage"))) {
					productSearch.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
				}
				
				List<Product> allProducts  = productService.searchProduct(productSearch);
				
				//Tổng số trang
				int totalPages = allProducts.size()/SIZE_OF_PAGE;
				if(allProducts.size() % SIZE_OF_PAGE >0) {
					totalPages++;
				}
				if(totalPages< productSearch.getCurrentPage()) {//Bấm search
					productSearch.setCurrentPage(1);
					
				}
				
				//Tổng số sản phẩm
				productSearch.setTotalItems(allProducts.size());
				productSearch.setSizeOfPage(SIZE_OF_PAGE);
				
				//Lấy danh sách sản phẩm trong 1 trang
				List<Product> products = new ArrayList<Product>();
				
				int firstIndex = (productSearch.getCurrentPage() -1) * SIZE_OF_PAGE;
				int count = 0;
				while(firstIndex < allProducts.size() && count < SIZE_OF_PAGE ) {
					products.add(allProducts.get(firstIndex));
					firstIndex++;
					count++;
				}
				
				
				List<Category> categories = categoryService.findAllActive();
				model.addAttribute("categories", categories);
				
//				List<Product> products = productService.searchProduct(productSearch);
				model.addAttribute("products", products);
				model.addAttribute("productSearch", productSearch);
				return "backend/product-list";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addProduct(final Model model) {

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		Product product = new Product();
		product.setCreateDate(new Date());
		model.addAttribute("product", product);

		return "backend/product-add";
	}

	@RequestMapping(value = "add-save", method = RequestMethod.POST)
	public String addSave(final Model model, @ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {

		productService.saveAddProduct(product, avatarFile, imageFiles);

		return "redirect:/admin/product/add";
	}

	@RequestMapping(value = "edit/{productId}", method = RequestMethod.GET)
	public String editCategory(final Model model, @PathVariable("productId") int productId) {

		// Lấy category trong db
		Product product = productService.getById(productId);
		product.setUpdateDate(new Date());
		model.addAttribute("product", product);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		return "backend/product-edit";
	}

	@RequestMapping(value = "edit-save", method = RequestMethod.POST)
	public String editSave(final Model model, @ModelAttribute("product") Product product,
			@RequestParam("avatarFile") MultipartFile avatarFile,
			@RequestParam("imageFiles") MultipartFile[] imageFiles) throws IOException {

		productService.saveEditProduct(product, avatarFile, imageFiles);

		return "redirect:/admin/product/list";
	}

	// Xóa
//	1.Xóa hẳn trong db
	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
	public String deleteProduct(final Model model, @PathVariable("productId") int productId) throws IOException {

		Product product = productService.getById(productId);
		productService.deleteProduct(product);

		return "redirect:/admin/product/list";
	}
//	2.Inactive
//	@RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
//	public String deleteProduct(final Model model,
//			@PathVariable("productId") int productId) {
//		
//		Product product = productService.getById(productId);
//		product.setStatus(false);
//		productService.saveOrUpdate(product);
//		
//		return "redirect:/admin/product/list";
//	}

}
