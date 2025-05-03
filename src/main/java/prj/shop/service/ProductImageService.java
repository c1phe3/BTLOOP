package prj.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import prj.shop.model.ProductImage;



@Service
public class ProductImageService extends BaseService<ProductImage> {


	@Override
	public Class<ProductImage> clazz() {
		// TODO Auto-generated method stub
		return ProductImage.class;
	}
	
	public List<ProductImage> getProductImagesByProductId(int productId){
		String sql = "SELECT * FROM tbl_product_image where product_id= " + productId;
		return super.executeNativeSql(sql);
	}
}
