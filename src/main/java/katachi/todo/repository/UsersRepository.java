package katachi.todo.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import katachi.todo.domain.model.FullNameModel;
import katachi.todo.domain.model.UsersModel;

@Mapper
//@Repository
public interface UsersRepository {

	public UsersModel getLoginUser(UserDetails username);
	public UsersModel getOneUser(int id);
	public List<UsersModel>getUserList();
	public List<UsersModel> getNowLogin();
	public List<FullNameModel> getFullName();

}


