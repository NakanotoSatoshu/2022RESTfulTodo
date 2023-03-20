package katachi.todo.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.CrudRepository;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.form.todoItemsForm;

@Mapper
public interface todoRepository extends CrudRepository<todoItemsModel, Long>{

	public todoItemsModel selectOne(String itemId);
	public int selectOne2(int id);
	public UsersModel getFullName2();
    List<todoItemsModel>selectMany();
	public void entryTheOne(int user_id,String item_name,Date registration_date,Date expire_date);
	public void updateOne(
			@Param("id")int id,
			@Param("user_id")int user_id,
			@Param("item_name")String item_name,
			@Param("expire_date")Date expire_date);
	public void deleteOne1(todoItemsForm todoItem);
    int  deleteOne(todoItemsForm todoItem);
	int updateFinishedDate(todoItemsForm todoItem);
	public todoItemsModel find(String param);
	public todoItemsForm save(todoItemsForm todoItem);
}
