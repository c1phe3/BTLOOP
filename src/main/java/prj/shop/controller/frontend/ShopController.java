package prj.shop.controller.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prj.shop.controller.BaseController;





@Controller
public class ShopController extends BaseController {


	
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String home(final Model model) {
		//1. Controller phat hien action cua request yeu cau du lieu la gi 
		//2. Yeu cau service lay (gui) dl tu (vao) database (table),
		//   tra ve data logic(variables, string,array list,..)
		//3. Ghep data (logic) voi view tra ve cho broswer (client)
	
		
		
		return "frontend/shop";
	}
//	@RequestMapping(value = "/product-detail/{productId}", method = RequestMethod.GET)
//	public String productDetail(final Model model,
//			@PathVariable("productId") int productId) {
//		//Lay san pham trong db
//		Product product = productService.getById(productId);
//		model.addAttribute("product", product);
//		
//		//Lay danh sach anh cua san pham 
//		List<ProductImage> productImages = productImageService.getProductImagesByProductId(productId);
//		model.addAttribute("productImages", productImages);
//		
//		return "frontend/product-detail";
//	}
}
