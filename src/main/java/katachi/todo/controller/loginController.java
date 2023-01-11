package katachi.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import katachi.todo.domain.service.todoLogic;
import katachi.todo.domain.service.usersLogic;

@Controller
//@RequestMapping("/???")

@Scope("session")
public class loginController {

	@Autowired
	usersLogic userLogic;

	@Autowired
	todoLogic todoLogic;

	@RequestMapping("/")
	public String getIndex(Model model) {
		return "login";
	}

	@RequestMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}

	/** ログイン画面を表示 */
	@PostMapping("/login")
	public String postLogin(Model model) {
	 return "redirect:todoList";
	}


















	//@PostMapping("/login")
	// public String WhoIsLoginNow(@ModelAttribute UsersModel UM,Model model) {
	// 	System.out.println("WHOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	//	List<UsersModel>loginUser = todoLogic.getNowLogin();
	//    	model.addAttribute("loginUser",loginUser);
	//    	log.info(loginUser.toString());
	//    	return "home" ;
	// }

	//@GetMapping("/home")
	//public String getHome(@ModelAttribute UsersModel UM, todoItemsForm FTDI, Model model) {

		// List<UsersModel> loginUser = userLogic.getNowLogin();
		//log.info(loginUser.toString());
		//System.out.println("NOWWWLOOOOGINNNNなううろぐいんんしてるううううう");
		//System.out.println("きみは" + loginUser);

		//メインTODOLISTの表示リスト獲得
	//	List<todoItemsModel> todoList = todoLogic.selectMany();
	//	log.info(todoList.toString());
	//	model.addAttribute("todoList", todoList);

	//	return "home";
	//}







	/** その他の例外処理 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		// 空文字をセット
		model.addAttribute("error", "");

		// メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}
}
