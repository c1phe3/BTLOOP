package prj.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import prj.shop.dto.Constants;
import prj.shop.dto.SearchModel;
import prj.shop.model.Product;
import prj.shop.model.ProductImage;


@Service
public class ProductService extends BaseService<Product> implements Constants {

	@Override
	public Class<Product> clazz() {
		// TODO Auto-generated method stub
		return Product.class;
	}
	
	public List<Product> findAllActive() {
		return super.executeNativeSql("SELECT * FROM tbl_product WHERE status=1");
	}
	
	//Search product
	public List <Product> searchProduct(SearchModel productSearch){
		String sql = "SELECT * FROM tbl_product p WHERE 1=1";
		//Tim theo status
		if(productSearch.getStatus() != 2) {//Co chon active or inactive
			sql += " AND p.status=" + productSearch.getStatus();
		}
		//Tim theo category
		if(productSearch.getCategoryId()!=0) {//Co chon category
			sql += " AND p.category_id="+ productSearch.getCategoryId();
		}
		
		//Tim theo keyword
		if(productSearch.getKeyword() != null) {
			String keyword = productSearch.getKeyword().toLowerCase();
			sql += " AND (LOWER(p.name) like '%" + keyword +"%'" +
		" OR LOWER(p.short_description) like'%" + keyword + "%'" +
		" OR LOWER(p.seo) like '%" + keyword + "%')";
		}
		
		//Tìm kiếm date to date
		String beginDate = productSearch.getBeginDate();
		String endDate = productSearch.getEndDate();
		if(beginDate != null || endDate != null) {
			sql += " AND p.create_date BETWEEN '" + beginDate + "' AND '"+ endDate + "'";
		}
		
		
		return super.executeNativeSql(sql);
	}
	//Phương thức kiểm tra (1) file có được upload không?
	public boolean isUploadFile(MultipartFile file) {
		if(file == null || file.getOriginalFilename().isEmpty()) {
			return false;//khong upload
		}
		return true;//co upload
	}
	//Phương thức kiểm tra danh sách file có upload file nào không?
	public boolean isUploadFiles(MultipartFile[] files) {
		if(files == null || files.length == 0) {
			return false; // khong upload files
		}
		return true;// co upload file
	}

	@Transactional
	public Product saveAddProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		//Luu avatar file
		if(isUploadFile(avatarFile)) {//Co upload file avatar
			//Luu file vao thu muc product/avatar
			String path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			File file = new File(path);
			avatarFile.transferTo(file);
			//Luu duong dan vao bang tbl_product
			product.setAvatar("Product/Avatar/"+ avatarFile.getOriginalFilename());
		}
		//Luu images file
		if(isUploadFiles(imageFiles)) {
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) {//File co upload
					//luu file vao thu muc Product/Image/
					String path =  FOLDER_UPLOAD + "Product/Image/" + imageFile.getOriginalFilename();
					File file= new File(path);
					imageFile.transferTo(file);
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/"+ imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					//Luu duong dan anh sang bang tbl_product_image
					product.addRelationalProductImage(productImage);
				}
			}
		}
		return super.saveOrUpdate(product);
	}
	@Transactional
	public Product saveEditProduct(Product product, MultipartFile avatarFile,
			MultipartFile[] imageFiles) throws IOException {
		//Lay product trong db
		Product dbProduct = super.getById(product.getId());
		//Luu avatar file moi neu nguoi dung co upload avatar
		if (isUploadFile(avatarFile)) {
			//Xoa avatar cu (xoa file avatar)
			String path  = FOLDER_UPLOAD + dbProduct.getAvatar();
			File file = new File(path);
			file.delete();
			
			//Luu file avatar moi vao thu muc Product/Avatar
			path = FOLDER_UPLOAD + "Product/Avatar/" + avatarFile.getOriginalFilename();
			file = new File(path);
			avatarFile.transferTo(file);
			//Luu duong dan cua avatar moi vao bang tbl_product
			product.setAvatar("Product/Avatar/" + avatarFile.getOriginalFilename());
		}
		else {// Nguoi dung khong upload avatar file
			//Giu nguyen file cu
			product.setAvatar(dbProduct.getAvatar());
		}
		if (isUploadFiles(imageFiles)) {
			//Duyet danh sach file images
			for(MultipartFile imageFile : imageFiles) {
				if(isUploadFile(imageFile)) {
					//Luu file vao thu muc Product/Image/
					String path = FOLDER_UPLOAD + "Product/Image/" +
					imageFile.getOriginalFilename();
					File file = new File(path);
					imageFile.transferTo(file);
					
					//Luu duong dan vao tbl product image
					ProductImage productImage = new ProductImage();
					productImage.setTitle(imageFile.getOriginalFilename());
					productImage.setPath("Product/Image/" + imageFile.getOriginalFilename());
					productImage.setStatus(Boolean.TRUE);
					productImage.setCreateDate(new Date());
					
					//Luu (doi tuong product iamge) duong dan anh sang bang tbl product image
					product.addRelationalProductImage(productImage);
				}
			}
		}
		return super.saveOrUpdate(product);
	}
	@Autowired 
	private ProductImageService productImageService;
	@Transactional
	public void deleteProduct(Product product) {
		//Lay danh sach anh cua product trong product image

		List<ProductImage> productImages = productImageService.getProductImagesByProductId(product.getId());
		// xoa lan luot anh trong tbl product imgae
		//Xoa lan luot cac duong dan anh trong tbl product image
		for (ProductImage productImage : productImages) {
			// xoa file trong thu muc product imgae
			String path = FOLDER_UPLOAD + productImage.getPath();
			File file = new File(path);
			file.delete();
			// xoa thong tin trong tbl product imade
//			product.removeRelationalProductImage(productImage); //restrict
		}
		// xoa avatar trong thu muc product/avatar
		String path = FOLDER_UPLOAD + product.getAvatar();
		File file = new File(path);
		file.delete();
		// Xoa product trong db
		super.delete(product);
	}
}
