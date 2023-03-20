package katachi.todo.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.io.JsonEOFException;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.domain.service.todoLogic;
import katachi.todo.domain.service.usersLogic;
import katachi.todo.form.GroupOrder;
import katachi.todo.form.todoItemsForm;
import katachi.todo.repository.todoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/todo")
public class itemRestController {

	private final todoRepository todoRepo;
	// private final TodoRepository2 todoRepo2;

	@Autowired
	usersLogic userLogic;

	@Autowired
	todoLogic todoLogic;

	@Autowired
	ModelMapper mDMP;
	
	/** UserListを取得 */
	@RequestMapping("/home")
    List<UsersModel> getUsers(   ) {
		System.out.println("たくさんのお客様がこられました");
		//ユーザ情報を取得
	//  todoItemsModel item = todoLogic.selectOne(itemId);
	  return userLogic.getUserList();
	}
	
	/** TODOを取得 */
	@GetMapping
	List<todoItemsModel> selectMany() {
		System.out.println("TodoListみたいなチャーハン食いたい");
		return todoRepo.selectMany();
	}	
	
	/** TODOを登録 */
	@PostMapping("/entry")
	todoItemsModel entryTheOne( 
			@Validated(GroupOrder.class) 
	        @RequestBody  
			todoItemsForm tDIF,
			todoItemsModel todo ,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			return (todo);
		}
			
		System.out.println("チャンネル登録" );	
		//System.out.println(tDIF);
		todo= mDMP.map(tDIF, todoItemsModel.class);
		Date d = new Date();
		todo.setRegistration_date(d);
		System.out.println(todo );
		//System.out.println(tDIF);
		 //全部にゲッターをつけて対処する
		return todoLogic.entryTheOne(todo.getUser_id(),todo.getItem_name(),todo.getRegistration_date(),todo.getExpire_date());
		//return   todo;
	}
	
	/** TODOを更新 */
	@PostMapping("/edit/{id}")
	todoItemsModel  update(
			@Validated(GroupOrder.class) 
			@RequestBody  todoItemsForm tDIF,
			todoItemsModel todo ,
			BindingResult bindingResult) throws ParseException ,JsonEOFException { 

		//System.out.println("人生をやり直せました～～～＠＠＠" + Id);
		System.out.println("これが新しい中華です " + todo);
		System.out.println("これが新しい粗品です " + tDIF);
		
		return todoLogic.updateOne(tDIF.getId(),tDIF.getUser_id(),tDIF.getItem_name(),tDIF.getExpire_date());
	}
	

	// @PostMapping( value="/edit" , params = "updateOne" )
//	@RequestMapping(
//			path = "/edit",
//			method = RequestMethod.POST)
//	public String editOne(@RequestBody  @Validated(GroupOrder.class)
//			todoItemsForm tDIF,
//			BindingResult bindingResult,
//			Model model) {
//		System.out.println("更新ボタンの処理");

	// 入力チェックに引っかかった場合、作業登録画面に戻る
	// if(bindingResult.hasErrors()) {
	// GETリクエスト用のメソッドを呼び出して、作業更新画面に戻ります
	// return getEdit(tDIF, model, Integer.toString(tDIF.getId()));
	// }

//		try {
//		log.info(tDIF.toString());
//	} catch (Exception e) {
//		      log.error("ユーザー更新でエラー", e);
//	}
//
//	    todoLogic.updateOne(
//	    		tDIF.getId(),
//	    		tDIF.getUser_id(),
//	    		tDIF.getItem_name(),
//	    		tDIF.getExpire_date());
//
//
//	    System.out.println(tDIF);
//	    System.out.println("DB　コネクションok");
//		return  "0";
//	}

	/** ユーザーを削除 */
	@PostMapping("/delete/{id}")
	int tDelete(@RequestBody @PathVariable("id") int id) {
		System.out.println("ゴミ捨てといて！");
		// TodoItemインスタンス生成
		todoItemsForm todoItem = new todoItemsForm();
		// フォームクラスをTodoItemクラスに変換(form=画面取得用, TodoItem=データ保存用)
		// 現在日時を取得
		Date d = new Date();
		todoItem.setId(id);
		todoItem.setUpdate_date_time(d);
		System.out.println("カッチカチのお焦げ捨てました！！");
		return todoRepo.deleteOne(todoItem);
	}

	/**
	 * アイテムコンプリート
	 * 
	 * @return
	 */
	// @CrossOrigin(origins = {"http://localhost:5173"})
	@PostMapping("/{id}")
	int Complete(@RequestBody @PathVariable("id") int id) {
		// TodoItemインスタンス生成
		todoItemsForm todoItem = new todoItemsForm();
		// 現在日時を取得
		Date d = new Date();
		System.out.println(d);
		todoItem.setId(id);
		todoItem.setFinished_date(d);
		todoItem.setUpdate_date_time(d);
		todoItem.getFinished_date();
		todoItem.getUpdate_date_time();
		System.out.println("完了した餃子が焼けました");
		return todoRepo.updateFinishedDate(todoItem);
	}

	/** アイテムインコンプリート */
	@PostMapping("/in{id}")
	int InComplete(@RequestBody @PathVariable("id") int id) {
		// TodoItemインスタンス生成
		todoItemsForm todoItem = new todoItemsForm();
		// 現在日時を取得
		Date d = new Date();
		todoItem.setId(id);
		todoItem.setFinished_date(null);
		todoItem.setUpdate_date_time(d);
		System.out.println("未完了のマクドいこか");
		return todoRepo.updateFinishedDate(todoItem);
	}
	
	@PostMapping("/search/{param}")
     todoItemsModel  search(
			@RequestBody 
			@PathVariable("param") String param,
			todoItemsForm tDIF, 
			HttpServletRequest request,  
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetails username) {
		System.out.println("検索～");
		//ユーザ情報を取得
		//UsersModel loginUser = userLogic.getLoginUser(username);
		//model.addAttribute("loginUser", loginUser);
		
        return  todoLogic.find(param) ;  
    }
}
