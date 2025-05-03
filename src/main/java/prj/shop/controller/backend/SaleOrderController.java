package prj.shop.controller.backend;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prj.shop.controller.BaseController;
import prj.shop.dto.Constants;
import prj.shop.dto.SearchModel;
import prj.shop.model.Category;
import prj.shop.model.Product;
import prj.shop.model.SaleOrder;
import prj.shop.service.ProductService;
import prj.shop.service.SaleOrderProductService;
import prj.shop.service.SaleOrderService;

@Controller
public class SaleOrderController extends BaseController implements Constants  {
	@Autowired
	private SaleOrderService saleOrderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SaleOrderProductService saleOrderProductService;
	
	@RequestMapping(value = "/admin/order/list", method = RequestMethod.GET)
	public String orderList(final Model model, final HttpServletRequest request) {
		BigDecimal totalSales = BigDecimal.ZERO;
		//SearchModel orderCodeSearch = new SearchModel();
		Map<Integer, List<Product>> orderProductsMap = new HashMap<Integer, List<Product>>();
		List<SaleOrder> saleOrders = saleOrderService.findAll(); 
		for(SaleOrder saleOrder : saleOrders) {
			totalSales = totalSales.add(saleOrder.getTotal());
			
			
			List<Product> products = saleOrderProductService.getProductsByOrderId(saleOrder.getId());
			//saleOrder.setProducts(products);
			orderProductsMap.put(saleOrder.getId(), products);
		}

		model.addAttribute("orderProductsMap", orderProductsMap);
		model.addAttribute("totalSales", totalSales);
		model.addAttribute("saleOrders", saleOrders);
		return "backend/order-list";
	}
	
	
	
	
	
	@RequestMapping(value = "delete/{saleOrderId}", method = RequestMethod.GET)
	public String deleteProduct(final Model model, @PathVariable("saleOrderId") int saleOrderId) throws IOException {

		
		SaleOrder saleOrder = saleOrderService.getById(saleOrderId);
		saleOrderService.delete(saleOrder);

		return "redirect:/admin/order/list";
	}
	
	@RequestMapping(value = "edit/{saleOrderId}", method = RequestMethod.POST)
	public String editProduct(final Model model, @PathVariable("saleOrderId") int saleOrderId) throws IOException{
		SaleOrder saleOrder = saleOrderService.getById(saleOrderId);
		 saleOrder.setStatus(true); 
	     saleOrderService.saveOrUpdate(saleOrder);
		return "redirect:/admin/order/list";
	}
	
	
	
	
	
}
