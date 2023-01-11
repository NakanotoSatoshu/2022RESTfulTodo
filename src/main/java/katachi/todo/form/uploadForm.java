package katachi.todo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class uploadForm {

    private MultipartFile multipartFile;

}