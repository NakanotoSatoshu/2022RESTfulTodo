package katachi.todo.controller;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import katachi.todo.domain.model.deleteModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.domain.service.todoLogic;
import katachi.todo.domain.service.usersLogic;
import katachi.todo.form.GroupOrder;
import katachi.todo.form.todoItemsForm;
import katachi.todo.repository.todoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class itemRestController {
	
	private final todoRepository todoRepo;
	
	@Autowired
	usersLogic userLogic;

	@Autowired
	todoLogic todoLogic;

	@Autowired
	ModelMapper mDMP;
	
	/** TODOを取得 */
	@GetMapping
	List<todoItemsModel>selectMany(){
		System.out.println("CROS通信OK!!!");
		return todoRepo.selectMany();
	}

	/** ユーザー削除画面モーダルウィンドウ */
	//@GetMapping("/delete/{id}")
	//public String restGetDelete(@RequestBody deleteModel del)  {

		//ユーザIDのチェック
	//	if(itemId != null && itemId.length() > 0) {

			//ユーザ情報を取得
	//		todoItemsModel item = todoLogic.selectOne(itemId);

			//TodoItemクラスをフォームクラスに変換
	//		tDIF.setId(item.getId());
	//		tDIF.setItem_name(item.getItem_name());

			//Modelに登録
	//		model.addAttribute("todoList", tDIF);
	//	}

	//	return "0";
	//}

	/** TODOを更新 */
//	@GetMapping("/edit")
//	public String getEdit(@RequestBody todoItemsForm tDIF,  @PathVariable("id") String itemId) {
//		//コンテンツ部分に編集画面を表示するための文字列を登録
//		//model.addAttribute("contents", "login/editTodoList :: editTodoList_contents");
//
//		System.out.println("Started GET EDIT " + itemId);
//		//UserId確認(デバッグ)
//		System.out.println("itemID = " + itemId);
//
//		//ユーザ一覧の生成
//		List<UsersModel> userList = userLogic.getUserList();
//
//		//Modelに作業一覧リストを登録
//		//model.addAttribute("userList", userList);
//
//		//ユーザIDのチェック
//		if(itemId != null && itemId.length() > 0) {
//
//		//ユーザ情報を取得
//		todoItemsModel item =  todoLogic.selectOne(itemId);
//
//		//TodoItemクラスをフォームクラスに変換
//		tDIF.setId(item.getId());
//		tDIF.setItem_name(item.getItem_name());
//		tDIF.setUser_id(item.getUser_id());
//		tDIF.setExpire_date(item.getExpire_date());
//		tDIF.setFinished_date(item.getFinished_date());
//
//			if(item.getFinished_date() != null) {
//				tDIF.setFinishedCheck(true);
//			}else {
//				tDIF.setFinishedCheck(false);
//			}
//			tDIF.setIs_delete(item.getIs_deleted());
//
//		//Modelに登録
//		//model.addAttribute("todoItemsForm", tDIF);
//		}
//
//		//作業登録画面に遷移
//		return "0";
//
//	}

	//@PostMapping( value="/edit" , params = "updateOne" )
	@RequestMapping(
			path = "/edit",
			method = RequestMethod.POST)
	public String editOne(@RequestBody  @Validated(GroupOrder.class)
			todoItemsForm tDIF,
			BindingResult bindingResult,
			Model model) {
		System.out.println("更新ボタンの処理");

		//入力チェックに引っかかった場合、作業登録画面に戻る
		//if(bindingResult.hasErrors()) {
			//GETリクエスト用のメソッドを呼び出して、作業更新画面に戻ります
			//return getEdit(tDIF, model, Integer.toString(tDIF.getId()));
		//}

//		try {
//		log.info(tDIF.toString());
//	} catch (Exception e) {
//		      log.error("ユーザー更新でエラー", e);
//	}

	    todoLogic.updateOne(
	    		tDIF.getId(),
	    		tDIF.getUser_id(),
	    		tDIF.getItem_name(),
	    		tDIF.getExpire_date());


	    System.out.println(tDIF);
	    System.out.println("DB　コネクションok");
		return  "0";
	}

	/** ユーザーを削除 */
    @PostMapping( "/delete")
	public String restDelete(@RequestBody deleteModel del)  {
    	//System.out.println(del);
    	//log.info(del.id);

    	int num = Integer.parseInt(del.id);
    	//TodoItemインスタンス生成
    	todoItemsForm todoItem = new todoItemsForm();

		//フォームクラスをTodoItemクラスに変換(form=画面取得用, TodoItem=データ保存用)

		//現在日時を取得
		Date d = new Date();
		todoItem.setId(num);
		todoItem.setUpdate_date_time(d);

    	todoLogic.deleteOne(todoItem);

		return "0";
	}

    /** アイテムコンプリート */
	@PostMapping("/complete")
	public String postComplete(@RequestBody deleteModel del) {

		int num = Integer.parseInt(del.id);

		//TodoItemインスタンス生成
		todoItemsForm todoItem = new todoItemsForm();

		//現在日時を取得
		Date d = new Date();
		todoItem.setId(num);
		todoItem.setFinished_date(d);
		todoItem.setUpdate_date_time(d);
		todoItem.getFinished_date();
		todoItem.getUpdate_date_time();

		todoLogic.updateFinishedDate(todoItem);

		return "0";
	}
	  /** アイテムインコンプリート */
		@PostMapping("/incomplete")
		public String postInComplete(@RequestBody deleteModel del) {

			int num = Integer.parseInt(del.id);

			//TodoItemインスタンス生成
			todoItemsForm todoItem = new todoItemsForm();

			//現在日時を取得
			Date d = new Date();
			todoItem.setId(num);
			todoItem.setFinished_date(null);
			todoItem.setUpdate_date_time(d);

			todoLogic.updateFinishedDate(todoItem);

			return "0";
		}
}
