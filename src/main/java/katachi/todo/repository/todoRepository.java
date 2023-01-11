package katachi.todo.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.form.todoItemsForm;

@Mapper
public interface todoRepository {

	public todoItemsModel selectOne(String itemId);
	public int selectOne2(int id);
	public UsersModel getFullName2();
	public List<todoItemsModel>selectMany();
	public void entryTheOne(todoItemsModel todoForMaped);
	public void updateOne(
			@Param("id")int id,
			@Param("user_id")int user_id,
			@Param("item_name")String item_name,
			@Param("expire_date")Date expire_date);
	public void deleteOne(todoItemsForm todoItem);
	public int updateFinishedDate(todoItemsForm todoItem);
	public List<todoItemsModel> find(String param);
}
