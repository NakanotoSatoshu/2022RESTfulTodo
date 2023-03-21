package katachi.todo.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "todo_items")
public class todoItemsForm {

	private int id ;
	
	@Range(min = 1 , groups=ValidGroup1.class)
	@NotNull(groups=ValidGroup2.class)
	//@NotBlank(groups=ValidGroup1.class)
	private int user_id;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min = 1, max = 100, groups=ValidGroup2.class)
	private String item_name;
	
	@NotNull(groups=ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expire_date ;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date finished_date;
	
	private int is_delete;
	private Date create_date_time;
	private Date update_date_time;
	private int priority;
	private Boolean finishedCheck;
}
