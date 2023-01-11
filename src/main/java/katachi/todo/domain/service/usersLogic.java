package katachi.todo.domain.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import katachi.todo.domain.model.FullNameModel;
import katachi.todo.domain.model.UsersModel;


public interface usersLogic {

	public UsersModel getLoginUser(UserDetails username);
	public UsersModel getOneUser(int id);
	public List<UsersModel>getUserList();
	public List<UsersModel>getNowLogin();
	public List<FullNameModel> getFullName();


}
