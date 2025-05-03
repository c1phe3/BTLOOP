package prj.shop.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prj.shop.controller.BaseController;



@Controller
public class AdminHomeController extends BaseController {
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String viewHomePage() {
		return "backend/home";
		
	}
	
	

}