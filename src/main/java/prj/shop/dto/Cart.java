package prj.shop.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Cart {
private ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	//Tim hang trong gio
	public int findProductById(int productId) {
		for (int i = 0; i <cartProducts.size(); i++) {
			if(cartProducts.get(i).getId()== productId) {
				return i;
			}
		}
		return -1;
	}
	//TInh tong tien cua gio hang
	public BigDecimal totalCartPrice() {
		BigDecimal total = BigDecimal.ZERO;
		for(CartProduct cartProduct : this.cartProducts) {
			total = total.add(cartProduct.totalPrice());
		}
		return total;
	}
	
	//Tong so luong hang(san pham) trong gio hang
	public BigInteger totalCartProduct() {
		BigInteger total = BigInteger.ZERO;
		for(CartProduct cartProduct : this.cartProducts) {
			total= total.add(cartProduct.getQuantity());
		}
		return total;
	}
	public Cart() {
		super();
	}
	public Cart(ArrayList<CartProduct> cartProducts) {
		super();
		this.cartProducts = cartProducts;
	}
	public ArrayList<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(ArrayList<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
}
