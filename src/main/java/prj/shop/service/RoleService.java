package prj.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import prj.shop.model.Role;



@Service
public class RoleService extends BaseService<Role> {

	@Override
	public Class<Role> clazz() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	@Transactional
	public void deleteRoleById(int id){
		super.deleteById(id);
	}
	@Transactional
	public void inactiveId(Role role){
		super.saveOrUpdate(role);
	}
	
	public Role getRoleByName(String name) {
		
		String sql = "SELECT * FROM tbl_role WHERE name='"+name+"'";
		List<Role> roles = super.executeNativeSql(sql);
		if (roles.size()>0) {
			return roles.get(0);
		}else {
			return new Role();
		}
			
	}
	public List<Role> findAllActive(){
		String sql =  "SELECT * FROM tbl_category WHERE status=1";
		return super.executeNativeSql(sql);
	}
	
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
