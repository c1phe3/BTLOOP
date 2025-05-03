package prj.shop.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

import prj.shop.dto.Cart;
import prj.shop.model.User;





public class BaseController {

	@ModelAttribute("title")
	public String projectTitle() {
		return  "Aviato Fashion Shop";
	}
	@ModelAttribute("totalCartProducts")
	public BigInteger getTotalCartProducts(final HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("cart") == null) {// Khong co gio hang
			return BigInteger.ZERO;
		} else {
			Cart cart = (Cart) session.getAttribute("cart");
			return cart.totalCartProduct();
		}
	}
/*
	 //Lay thong tin cua user dang nhap
		@ModelAttribute("loginedUser")
		public User getLoginedUser() {

			Object loginedUser = 
					SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (loginedUser != null && loginedUser instanceof UserDetails) {
				User user = (User) loginedUser;
				return user;
			}
			return new User();
		}
		// Kiem tra da login hay chua?
			@ModelAttribute("isLogined")
			public boolean isLogined() {
				Object loginedUser = SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();

		if (loginedUser != null && loginedUser instanceof UserDetails) {
					return true;
				}
				return false;
			}
*/
}
