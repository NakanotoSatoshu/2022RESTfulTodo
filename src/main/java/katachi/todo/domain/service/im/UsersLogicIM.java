package katachi.todo.domain.service.im;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import katachi.todo.domain.model.FullNameModel;
import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.service.usersLogic;
import katachi.todo.repository.UsersRepository;

@Service
public class UsersLogicIM implements usersLogic {

	@Autowired
	public UsersRepository usersRepo;

	@Override
	public UsersModel getLoginUser(UserDetails username){
		return usersRepo.getLoginUser(username);
	}
	@Override
	public List<UsersModel>getUserList(){
		return usersRepo.getUserList();
	}
	@Override
	public List<UsersModel>getNowLogin(){
		return usersRepo.getNowLogin();
	}
	@Override
	public UsersModel getOneUser(int id) {
		return usersRepo.getOneUser(id);
	}
	@Override
	public List<FullNameModel> getFullName() {
		// TODO 自動生成されたメソッド・スタブ
		return usersRepo.getFullName();
	}



}
