package prj.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import prj.shop.dto.SearchModel;
import prj.shop.model.Product;
import prj.shop.model.UserRole;



@Service
public class UserRoleService extends BaseService<UserRole> {

	@Override
	public Class<UserRole> clazz() {
		// TODO Auto-generated method stub
		return UserRole.class;
	}
	//@Transactional
	//public void deleteRoleById(int id){
	//	super.deleteById(id);
	//}
	
	//public List <UserRole> UpdateRole(int userId, int roleId){
		//String sql = "INSERT INTO tbl_user_role (userId, roleId) VALUES (user, role)";
		//return super.executeNativeSql(sql);
	//}
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	@Transactional public void updateRole(int userId, int roleId) { String sql = "INSERT INTO tbl_user_role (user_id, role_id) VALUES (?, ?)"; jdbcTemplate.update(sql, userId, roleId); }
	//@Transactional
	//public void inactiveId(UserRole userrole){
		//super.saveOrUpdate(userrole);
	//}
	
	/*
	 @Transactional
	    public void assignRoleToUser(Long userId, Long roleId) {
	        // Tìm người dùng theo ID
	        User user = super.findById(userId);

	        // Tìm vai trò theo ID
	        Role role = findById(roleId);

	        // Gán vai trò vào danh sách vai trò của người dùng và lưu lại
	        if (user != null && role != null) {
	            user.getRoles().add(role); // Thêm vai trò vào danh sách vai trò của người dùng
	            super.saveOrUpdate(user);  // Cập nhật người dùng với vai trò mới
	        }
	    }
	*/
	
}
