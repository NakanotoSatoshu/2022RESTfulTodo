package katachi.todo.form;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//@Entity
//@Table(name = "users")
public class UsersForm {

	@Id
	@NotNull
	private int id ;

	//private String loginUser;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String family_name;

	@NotNull
	private String first_name;

	private String authority;

	private boolean is_deleted;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
	private Date create_date_time;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
	private Date update_date_time;



}
