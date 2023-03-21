package katachi.todo.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "todo_items")
public class todoItemsModel {

	private int id ;
	private int user_id;
	private String userName;
	private String fullName;
	private String family_name;
	private String first_name;
	private String item_name;
	private Date registration_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expire_date ;
	
	private Date finished_date;
	private int is_deleted;
	private Date create_date_time;
	private Date update_date_time;
	private int priority;
	private UsersModel user;
}
