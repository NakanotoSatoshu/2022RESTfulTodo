package katachi.todo.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.todo.domain.model.UsersModel;
import katachi.todo.domain.model.todoItemsModel;
import katachi.todo.domain.service.todoLogic;
import katachi.todo.domain.service.usersLogic;
import katachi.todo.form.GroupOrder;
import katachi.todo.form.todoItemsForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping
@RequiredArgsConstructor
@Controller
//@RequestMapping("/???")
@Slf4j
//@Scope("session")
public class ItmeController {

	@Autowired
	usersLogic userLogic;

	@Autowired
	todoLogic todoLogic;

	@Autowired
	ModelMapper mDMP;

	/*画像あっぷ*/
	private String filename;
	@PostMapping("/upload")
	public String handleFileUpload(Model model, Locale locale,@RequestParam("file") MultipartFile multipartFile) {
		System.out.println(File.separator);
		Path path = Paths.get("").toAbsolutePath();
		StringBuffer filePath = new StringBuffer(path.toString()+"/images");
		String name = multipartFile.getOriginalFilename();
		String ex = name.substring(name.lastIndexOf("."));
		File uploadDir = mkdirs(filePath,ex);
		try {
			ex = name.substring(name.lastIndexOf("."));
	    	File uploadFile = new File(uploadDir.getPath());
	    	byte[] bytes = multipartFile.getBytes();
	    	BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
	    	uploadFileStream.write(bytes);
	    	uploadFileStream.close();

	    	filename = uploadFile.getName();
		} catch (Exception e){
			System.out.println(e );
		} catch (Throwable t) {
			System.out.println("error");
	    }
		return "redirect:/home";
	}
	private File mkdirs(StringBuffer filePath,String ex){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		File uploadDir = new File(filePath.toString(), "files/"+sdf.format(now)+ex);
		int prefix = 0;
		while(uploadDir.exists()){
			prefix++;
			uploadDir = new File(filePath.toString(), "files/"+sdf.format(now)+"-"+String.valueOf(prefix)+ex);
		}
		return uploadDir;
	}

//	 @ResponseBody   // テンプレートは使用せずに、メソッドで返却したものBodyとして扱う指定
//	   @PostMapping(value = "upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	    public Object upload(@RequestPart("file") MultipartFile filePart) throws IOException {
//		 // ファイルの場合は、 `@RequestPart` を使うことを注意
//		 String baka = filePart.toString();
//	        System.out.println(baka);
//	        File file = File.createTempFile(UUID.randomUUID().toString(), filePart.getOriginalFilename());
//	        // ファイル名を取得し、Fileインスタンスを生成
//	        filePart.transferTo(file.toPath());
//	        byte[] bytes = Files.readAllBytes(file.toPath()); //ファイルの読み込み
//
//
//	        HttpHeaders httpHeaders = new HttpHeaders();
//	        httpHeaders.setContentType(MediaType.parseMediaType(Files.probeContentType(file.toPath())));
//	        // ファイル名からMimeTypeを取得する
//	        httpHeaders.setContentLength(bytes.length);
//
//	        return new HttpEntity<>(bytes, httpHeaders); //レスポンス
//	    }

//	@PostMapping("/upload")
//	public String upload(@RequestParam("upload_file") MultipartFile multipartFile, Model model) {
//	    model.addAttribute("originalFilename", multipartFile.getOriginalFilename());
//
//	    return "result";
//	}

	//@ResponseBody
	@GetMapping("/home")
	public String getHome(@ModelAttribute UsersModel UM, todoItemsForm tDIF, Model model,@AuthenticationPrincipal UserDetails username) {

		//画像パスを取得

		//ユーザ情報を取得
		UsersModel loginUser = userLogic.getLoginUser(username);
		model.addAttribute("loginUser", loginUser);

		//メインTODOLISTの表示リスト獲得
		List<todoItemsModel> todoList = todoLogic.selectMany();

		model.addAttribute("todoList", todoList);
		model.addAttribute("fileName",filename);

		return "home";
	}

//	@GetMapping("/todoList")
//	public String getTodoList(Model model) {
//		//コンテンツ部分に作業一覧を表示するための文字列を登録
//		model.addAttribute("contents", "login/todoList :: todoList_contents");
//
//		//メインTODOLISTの表示リスト獲得
//		List<todoItemsModel> todoList = todoLogic.selectMany();
//		//todoItemsModel todoList = todoLogic.selectMany();
//		log.info(todoList.toString());
//		model.addAttribute("todoList", todoList);
//
//		return "login/home";
//	}

	@GetMapping("/entry")
	public String getEntry(@ModelAttribute todoItemsForm FTDI, Model model) {
		//コンテンツ部分にユーザ一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/entry :: entry_contents");

		//ユーザ一覧の生成
		List<UsersModel> usersList = userLogic.getUserList();
		log.info(usersList.toString());

		model.addAttribute("usersList", usersList);

		return "entry";
	}

	@PostMapping("/entry")
	public String entryTheOne(@ModelAttribute @Validated(GroupOrder.class) todoItemsForm tDIF, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return getEntry(tDIF, model);
		}

		todoItemsModel todoForMaped = mDMP.map(tDIF, todoItemsModel.class);

		Date d = new Date();
		todoForMaped.setRegistration_date(d);

		log.info(todoForMaped.toString());

		todoLogic.entryTheOne(todoForMaped);

		return "redirect:/home";
	}

//	@GetMapping("/edit/{id}")
//	public String getEdit(@ModelAttribute todoItemsForm tDIF, Model model, @PathVariable("id") String itemId) {
//		//コンテンツ部分に編集画面を表示するための文字列を登録
//		//model.addAttribute("contents", "login/editTodoList :: editTodoList_contents");
//		System.out.println("Started GET EDIT " + itemId);
//		//UserId確認(デバッグ)
//		System.out.println("itemID = " + itemId);
//
//		//ユーザ一覧の生成
//		List<UsersModel> userList = userLogic.getUserList();
//
//		//Modelに作業一覧リストを登録
//		model.addAttribute("userList", userList);
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
//		model.addAttribute("todoItemsForm", tDIF);
//		}
//
//		//作業登録画面に遷移
//		return "edit";
//
//	}

//	@PostMapping( value="/edit" , params = "updateOne" )
//	public String editOne(@ModelAttribute @Validated(GroupOrder.class) todoItemsForm tDIF, BindingResult bindingResult,Model model) {
//		System.out.println("更新ボタンの処理");
//
//		//入力チェックに引っかかった場合、作業登録画面に戻る
//		//if(bindingResult.hasErrors()) {
//			//GETリクエスト用のメソッドを呼び出して、作業更新画面に戻ります
//			//return getEdit(tDIF, model, Integer.toString(tDIF.getId()));
//		//}
//
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
//		return  "redirect:home";
//	}

	/** ユーザーを削除 */
	//@GetMapping("/delete/{id}")
	//public String getDelete(@ModelAttribute todoItemsForm tDIF, Model model, @PathVariable("id") String itemId)  {

	//	model.addAttribute("contents", "login/delete :: delete_contents");

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

	//	return "delete";
//	}

    /** ユーザーを削除 */
	//@PostMapping("/delete")
	//public String deleteOne(@ModelAttribute todoItemsForm tDIF, Model model) {

		//TodoItemインスタンス生成
	//	todoItemsForm todoItem = new todoItemsForm();

		//フォームクラスをTodoItemクラスに変換(form=画面取得用, TodoItem=データ保存用)

		//現在日時を取得
	//	Date d = new Date();
	//	todoItem.setId(tDIF.getId());
	//	todoItem.setUpdate_date_time(d);

	//	todoLogic.deleteOne(todoItem);

  //  return "redirect:/home" ;

	//}

//	@GetMapping("/complete/{id}")
//	public String getComplete(@ModelAttribute todoItemsForm tDIF, Model model,
//	                                        @PathVariable("id") String itemId, RedirectAttributes redirectAttributes) {
//
//		//TodoItemインスタンス生成
//		todoItemsForm todoItem = new todoItemsForm();
//				//フォームクラスをTodoItemクラスに変換(form=画面取得用, TodoItem=データ保存用)
//				//現在日時を取得
//				Date d = new Date();
//				todoItem.setId(tDIF.getId());
//				todoItem.setFinished_date(d);
//				todoItem.setUpdate_date_time(d);
//
//				try {
//					//更新処理
//					boolean result = todoLogic.updateFinishedDate(todoItem);
//					if(result) {
//						System.out.println("更新成功");
//					}else{
//						redirectAttributes.addFlashAttribute("result", "更新失敗");
//						//getErrorPageにリダイレクト
//						return "redirect:/getErrorPage";
//
//					}
//				}catch(DataAccessResourceFailureException e) {
//					model.addAttribute("result", "更新失敗");
//				}t
//				//作業一覧画面表示
//				model.addAttribute("result", "更新失敗");
//
//				return "redirect:/home";
//	}

//	@GetMapping("/incomplete/{id}")
//	public String getInComplete(@ModelAttribute todoItemsForm tDIF, Model model, @PathVariable("id") String itemId, RedirectAttributes redirectAttributes) {
//
//		//ItemId確認(デバッグ)
//		System.out.println("itemID = " + itemId);
//
//		//TodoItemインスタンス生成
//		todoItemsForm todoItem = new todoItemsForm();
//		//フォームクラスをTodoItemクラスに変換(form=画面取得用, TodoItem=データ保存用)
//		//現在日時を取得
//		Date d = new Date();
//		todoItem.setId(tDIF.getId());
//		todoItem.setFinished_date(null);
//		todoItem.setUpdate_date_time(d);
//
//		try {
//			//更新処理
//			boolean result = todoLogic.updateFinishedDate(todoItem);
//			if(result) {
//				System.out.println("更新成功");
//			}else{
//				redirectAttributes.addFlashAttribute("result", "更新失敗");
//				//getErrorPageにリダイレクト
//				return "redirect:/getErrorPage";
//
//			}
//		}catch(DataAccessResourceFailureException e) {
//			model.addAttribute("result", "更新失敗");
//		}
//		//ユーザ一覧画面表示
//		return "redirect:/home";
//	}

	@PostMapping("/search")
	public String search(@ModelAttribute todoItemsForm tDIF, HttpServletRequest request,  Model model,RedirectAttributes redirectAttributes,@AuthenticationPrincipal UserDetails username) {


		//コンテンツ部分に作業一覧を表示するための文字列を登録
		model.addAttribute("contents", "login/searchTodoList :: searchTodoList_contents");

		//ユーザ情報を取得
		UsersModel loginUser = userLogic.getLoginUser(username);
		model.addAttribute("loginUser", loginUser);

        String param = request.getParameter("str");

        if(param == null) {
        	return "redirect:/home";
        }else {
        	model.addAttribute("value", param);
        	List<todoItemsModel> list = todoLogic.find(param);
        	model.addAttribute("todoList", list);
        	//redirectAttributes.addFlashAttribute(list);
        }
        return  "home";
    }


}
