package katachi.todo.domain.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Entity
//@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel {

	//@Id
	@NotNull
	private int id ;

	@NotNull
	private String username;

	@NotNull
	private String password;

	private String fullName;

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
