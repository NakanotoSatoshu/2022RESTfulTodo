package katachi.todo.domain.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.form.todoItemsForm;

public interface todoLogic {

	public List<todoItemsModel>selectMany();
	public todoItemsModel selectOne(String itemId);
	public int selectOne2(int id);
	public UsersModel getFullName2();
	todoItemsModel entryTheOne(
			@Param("user_id")int user_id,
			@Param("item_name")String item_name,
			@Param("registration_date")Date registration_date,
			@Param("expire_date")Date expire_date);
	public todoItemsModel updateOne(
			@Param("id")int id,
			@Param("user_id")int user_id,
			@Param("item_name")String item_name,
			@Param("expire_date")Date expire_date);
	public void deleteOne(todoItemsForm todoItem);
	public boolean updateFinishedDate(todoItemsForm todoItem) ;
	public todoItemsModel find(String param);
	public todoItemsModel toString(todoItemsModel todo);
	todoItemsModel toString(todoItemsForm tDIF);
}
