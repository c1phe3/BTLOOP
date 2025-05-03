package prj.shop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




@Entity
@Table(name= "tbl_user" )
public class User extends BaseModel /*implements UserDetails*/{

	@Column(name= "username", length= 120, nullable =false)
	private String username;
	
	@Column(name= "password", length= 120, nullable =false)
	private String password;
	
	@Column(name ="name", length = 120 , nullable = true)
	private String name;
	
	@Column(name= "email", length= 200, nullable =true)
	private String email;

	@Column(name= "mobile", length= 60, nullable =true)
	private String mobile;
	
	@Column(name= "address", length= 300, nullable =true)
	private String address;
	
	@Column(name= "avatar", length= 300, nullable =true)
	private String avatar;
	
	@Column(name= "description", length= 200, nullable =true)
	private String description;
	//
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private List<Role> roles = new ArrayList<Role>();
	
	//Add and rmove elements out of realational user-role list
	public void addRelationalUserRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}
	
	public void deleteRelationalUserRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}
	//----------------Mapping one-to-many : tbl_user  - to - tbl_sale_order--------------//
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SaleOrder> saleOrders = new HashSet<SaleOrder>();
	
	
	public void addRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.add(saleOrder);
		saleOrder.setUser(null);
	}
	public void removeRelationalSaleOrder(SaleOrder saleOrder) {
		saleOrders.remove(saleOrder);
		saleOrder.setUser(null);
	}
	// ----------------Mapping one-to-many : tbl_category- to -
		// tbl_product--------------//
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateCategory")
		private List<Category> createCategories = new ArrayList<Category>();

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateCategory")
		private List<Category> updateCategories = new ArrayList<Category>();
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateProduct")
		private List<Product> createProducts = new ArrayList<Product>();

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateProduct")
		private List<Product> updateProducts = new ArrayList<Product>();
		//------------------------------------------------------------
//		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//		private Set<Product> userCreateProducts= new HashSet<Product>();
//		
//		
//		public void addRelationalUserProduct(SaleOrder saleOrder) {
//			saleOrders.add(saleOrder);
//			saleOrder.setUser(null);
//		}
//		public void removeRelationalSaleOrder(SaleOrder saleOrder) {
//			saleOrders.remove(saleOrder);
//			saleOrder.setUser(null);
//		}
//		
		
		
		// -------------Mapping many to one : product-to-category--------------------//
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "create_by")
		private User userCreateUser;
		// -------------Mapping many to one : product-to-category--------------------//
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "update_by")
		private User userUpdateUser;
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateUser")
		private List<User> createUsers = new ArrayList<User>();

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateUser")
		private List<User> updateUsers = new ArrayList<User>();
		
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userCreateRole")
		private List<Role> createRoles = new ArrayList<Role>();

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userUpdateRole")
		private List<Role> updateRoles = new ArrayList<Role>();
		
		
	
	public User() {
		super();
	}
	

	public Set<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(Set<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUserCreateUser() {
		return userCreateUser;
	}

	public void setUserCreateUser(User userCreateUser) {
		this.userCreateUser = userCreateUser;
	}

	public User getUserUpdateUser() {
		return userUpdateUser;
	}

	public void setUserUpdateUser(User userUpdateUser) {
		this.userUpdateUser = userUpdateUser;
	}
/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
*/			
}
