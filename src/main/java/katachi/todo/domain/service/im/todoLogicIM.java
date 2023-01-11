package katachi.todo.domain.service.im;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.domain.service.todoLogic;
import katachi.todo.form.todoItemsForm;
import katachi.todo.repository.todoRepository;

@Service
public class todoLogicIM implements todoLogic {

	@Autowired
	public todoRepository todoRepo;

	@Override
	public List<todoItemsModel>selectMany(){
		return todoRepo.selectMany();
	}

	@Override
	public todoItemsModel selectOne(String itemId) {
		return todoRepo.selectOne(itemId);
	}

	@Override
	public void entryTheOne(todoItemsModel todoForMaped) {
		todoRepo.entryTheOne(todoForMaped);
	}

	@Override
	public void updateOne(int id, int user_id,String item_name, Date expire_date) {
		todoRepo.updateOne(id,user_id,item_name, expire_date);

	}

	@Override
	public void deleteOne(todoItemsForm todoItem) {
		todoRepo.deleteOne(todoItem);
	}

	@Override
	public boolean updateFinishedDate(todoItemsForm todoItem) {
			//1件更新
			int rowNumber = todoRepo.updateFinishedDate(todoItem);

			//判定用変数
			boolean result = false;
			if(rowNumber > 0) {
				//update成功
				result = true;
			}
			return result;
	}

	@Override
	public List<todoItemsModel> find(String param) {
		return todoRepo.find(param);
	}

	@Override
	public int selectOne2(int id) {
		return todoRepo.selectOne2(id);
	}

	@Override
	public UsersModel getFullName2(){
		// TODO 自動生成されたメソッド・スタブ
		return todoRepo.getFullName2();
	}





}
