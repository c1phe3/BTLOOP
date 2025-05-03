package prj.shop.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import prj.shop.model.Product;
import prj.shop.model.ProductImage;
import prj.shop.model.SaleOrderProduct;



@Service
public class SaleOrderProductService extends BaseService<SaleOrderProduct> {

	@Override
	public Class<SaleOrderProduct> clazz() {
		// TODO Auto-generated method stub
		return SaleOrderProduct.class;
	}
	
	public List<Product> getProductsByOrderId(int orderId) {
        String sql = "SELECT p FROM Product p JOIN SaleOrderProduct sop ON p.id = sop.product.id WHERE sop.saleOrder.id = :orderId";
        Query query = entityManager.createQuery(sql, Product.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }
	
	//public List<String> findAllActiveNames() {
	 //   String sql = "SELECT name FROM tbl_category WHERE status = 1";
	  //  return super.executeNativeSql(sql); 
	//}
	
}
