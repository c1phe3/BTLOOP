package prj.shop.controller.frontend;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import prj.shop.controller.BaseController;
import prj.shop.dto.Cart;
import prj.shop.dto.CartProduct;
import prj.shop.dto.Contact;
import prj.shop.model.Product;
import prj.shop.model.SaleOrder;
import prj.shop.model.SaleOrderProduct;
import prj.shop.model.User;
import prj.shop.service.ProductService;
import prj.shop.service.SaleOrderService;



@Controller
public class CartController extends BaseController {
	@Autowired
	private ProductService productService;

	@Autowired
	private SaleOrderService saleOrderService;
	
	@RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> addToCart(@RequestBody CartProduct cartProduct,
			final HttpServletRequest request) {
		Cart cart = null;		// Kiem tra xem co gio hang hay chua
		// Lay gio hang (lay session)
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") == null) {// Chua co gio hang
			// Tao gio hang moi
			cart = new Cart();
			session.setAttribute("cart", cart);
		} else {// Da co gio hang
			cart = (Cart) session.getAttribute("cart");
		}
		// thêm hàng vào giỏ
		// +Kiểm tra sản phẩm đã có trong giỏ hàng chưa
		int pIndex = cart.findProductById(cartProduct.getId());
		if (pIndex == -1) {// Sản phẩm chưa có trong giỏ hàng

			// +Lấy thông tin của sản phẩm từ db
			Product dbProduct = productService.getById(cartProduct.getId());

			cartProduct.setName(dbProduct.getName());
			cartProduct.setPrice(dbProduct.getPrice());
			cartProduct.setAvatar(dbProduct.getAvatar());
			// Thêm mới sản phẩm vào giỏ hàng
			cart.getCartProducts().add(cartProduct);
		} else { // Sản phẩm đã có trong giỏ hàng
			cart.getCartProducts().get(pIndex).updateQuantity(cartProduct.getQuantity());

		}
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("message", "Đã thêm "+ cartProduct.getQuantity()+" " + cartProduct.getName() + " vào giỏ hàng");
		jsonResult.put("totalCartProducts", cart.totalCartProduct());
		return ResponseEntity.ok(jsonResult);
	}

	@RequestMapping(value = "/cart-view", method = RequestMethod.GET)
	public String cartView(final Model model, final HttpServletRequest request) {

		Cart cart = null;
		BigInteger totalCartProducts = BigInteger.ZERO;
		BigDecimal totalCartPrice = BigDecimal.ZERO;
		HttpSession session = request.getSession();
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
			totalCartProducts = cart.totalCartProduct();
			totalCartPrice = cart.totalCartPrice();
		}
		model.addAttribute("cart", cart);
		model.addAttribute("totalCartProducts", totalCartProducts);
		model.addAttribute("totalCartPrice", totalCartPrice);

		return "frontend/cart-view";
	}

	@RequestMapping(value = "/update-product-quantity", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateProductQuantity(@RequestBody CartProduct cartProduct,
			final HttpServletRequest request) {

		Map<String, Object> jsonResult = new HashMap<String, Object>();

		Cart cart = null;
		// Lay gio hang (lay session)
		HttpSession session = request.getSession();
		cart = (Cart) session.getAttribute("cart");

		// +Tim san pham trong gio hang de cap nhat so luong
		int pIndex = cart.findProductById(cartProduct.getId());
		if (cartProduct.getQuantity().longValue() != -1
				|| cart.getCartProducts().get(pIndex).getQuantity().longValue() > 1) {

			// Cap nhat so luong
			cart.getCartProducts().get(pIndex).updateQuantity(cartProduct.getQuantity());

			// session.setAttribute("cart", cart);
		}
		jsonResult.put("code", 200);
	jsonResult.put("totalCartPrice", toCurrency(cart.totalCartPrice()));
		jsonResult.put("newQuantity", cart.getCartProducts().get(pIndex).getQuantity());
		jsonResult.put("productId", cartProduct.getId());
		jsonResult.put("totalCartProducts", cart.getCartProducts());
		jsonResult.put("totalPrice", toCurrency(cart.getCartProducts().get(pIndex).totalPrice()));
		return ResponseEntity.ok(jsonResult);
	}

	public StringBuilder toCurrency(BigDecimal num) {

		String str1 = "";
		str1 += num.longValue();
		StringBuilder str = new StringBuilder(str1);
		int i = str.length();
		int j = 0;
		while (i > 0) {
			j++;
			i--;
			if (i > 0 && j % 3 == 0) {
				str = str.insert(i, ',');
			}
		}
		return str;
	}
	
	// Luu gio hang
	@RequestMapping(value = "/place-order", method = RequestMethod.POST)
	ResponseEntity<Map<String, Object>> placeOrder(@RequestBody Contact customer, final HttpServletRequest request)
			throws IOException {

		Map<String, Object> jsonResult = new HashMap<String, Object>();

		// Kiem tra thong tin customer bat buoc
		if (customer.getName() == null || customer.getName().length() <= 0) {
			jsonResult.put("message", "Bạn chưa nhập họ tên");
		} else if (customer.getMobile() == null || customer.getMobile().length() <= 0) {
			jsonResult.put("message", "Bạn chưa nhập số điện thoại");
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("cart") == null) {
				jsonResult.put("message", "Bạn chưa có giỏ hàng");
			} else {
				Cart cart = (Cart) session.getAttribute("cart");
				// Luu cac san pham trong gio hang vao DB: tbl_sale_order_product
				SaleOrder saleOrder = new SaleOrder();
				for (CartProduct productCart : cart.getCartProducts()) {
					SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
					// Lay product trong db
					Product dbProduct = productService.getById(productCart.getId());
					saleOrderProduct.setProduct(dbProduct);
					saleOrderProduct.setQuantity(productCart.getQuantity().intValue());

					saleOrder.addRelationalSaleOrderProduct(saleOrderProduct);
				}
				// Luu don hang vao tbl_sale_order
				Calendar cal = Calendar.getInstance();
				String code = customer.getMobile() + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH)
						+ cal.get(Calendar.DAY_OF_MONTH);
				saleOrder.setCode(code);

				User user = new User();
				user.setId(1);
				saleOrder.setUser(user);

				saleOrder.setCustomerName(customer.getName());
				saleOrder.setCustomerMobile(customer.getMobile());
				saleOrder.setCustomerEmail(customer.getEmail());
				saleOrder.setCustomerAddress(customer.getAddress());

				saleOrder.setCreateDate(new Date());
				saleOrder.setTotal(cart.totalCartPrice());
				saleOrderService.saveOrder(saleOrder);
				

				jsonResult.put("message", "Bạn đã đặt hàng thành công, cảm ơn bạn");

				// Xoa gio hang sau khi da dat hang
				cart = new Cart();
				session.setAttribute("cart", cart);
			}

		}
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = "product-cart-delete/{cartProduct.id}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> removeProduct(@RequestBody CartProduct cartProduct, final HttpServletRequest request) {
	    Map<String, Object> jsonResult = new HashMap<String, Object>();
	    Cart cart = null;
	    HttpSession session = request.getSession();
	    cart = (Cart) session.getAttribute("cart");

	    // Tìm sản phẩm trong giỏ hàng để xóa
	    int pIndex = cart.findProductById(cartProduct.getId());
	    if (pIndex != -1) {
	        cart.getCartProducts().remove(pIndex);
	    }

	    jsonResult.put("code", 200);
	    jsonResult.put("totalCartPrice", toCurrency(cart.totalCartPrice()));
	    jsonResult.put("totalCartProducts", cart.totalCartProduct());

	    return ResponseEntity.ok(jsonResult);
	}
	
	
}

