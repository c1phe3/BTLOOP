package prj.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import prj.shop.dto.Constants;
import prj.shop.model.Category;
import prj.shop.model.User;



@Service
public class UserService extends BaseService<User> implements Constants {

	@Override
	public Class<User> clazz() {
		// TODO Auto-generated method stub
		return User.class;
	}
	public List<User> findAllActive(){
		String sql =  "SELECT * FROM tbl_user";
		return super.executeNativeSql(sql);
	}
	
	//Phương thức kiểm tra (1) file có được upload không?
	public boolean isUploadFile(MultipartFile file) {
		if(file == null || file.getOriginalFilename().isEmpty()) {
			return false;//khong upload
		}
		return true;//co upload
	}
			
	@Transactional
	public User saveAddUser(User user,MultipartFile avatarFile)throws IOException{
	//Luu avatar file
		if(isUploadFile(avatarFile)) {//Co upload file avatar
			//Luu file vao thu muc user/avatar
			String path = FOLDER_UPLOAD + "User/Avatar/" + avatarFile.getOriginalFilename();
			File file = new File(path);
			avatarFile.transferTo(file);
			//Luu duong dan vao bang tbl_user
			user.setAvatar("User/Avatar/"+ avatarFile.getOriginalFilename());
		}
		return super.saveOrUpdate(user);
	}
	
	 @Transactional
	   public void deleteById(int userId) {
	      super.deleteById(userId); 
	   }
}
