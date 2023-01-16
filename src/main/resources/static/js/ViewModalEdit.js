app.component('modal-edit', {  
	props: {   },
	template: `
	<!-- モーダルウィンドウ外側 -->
	<div class="overlay" id="js-overlay"></div>
	<!-- モーダルウィンドウ外側 -->
    <!-- 更新モーダルウィンドウ 別コンポーネント化VeeValidate化する-->
		<div class="modal2  animated  fadeIn" id="js-modal2">
			  <div class="modal-close__wrap">
				<button class="modal-close" id="js-close2">
					  <span></span>
					  <span></span>
				</button>
				
				<div class="align-items-center">
				<form  method="post" @submit.prevent="updateOne" >
			<!--------------------------------------------------------------------------------- 項目名の入力エリア -->
							<div class="form-group">
								<label for="item_name">項目名</label>
								<input type="hidden" class="form-control"/>
								<input type="text" class="form-control modalName2" v-model="item_name" />
							<span class="text-danger">item_name error</span>
							</div>
			<!--------------------------------------------------------------------------------- 担当者の入力エリア -->
							 <div class="form-group" >
								<label for="user_id">担当者</label>
								<select class="form-control"  >
									<option   value="0" selected></option>
									<option   value="1"></option>
									<option ></option>
								</select>
								<span class="text-danger">userId error</span>
							</div>
							<div class="form-group" >
								<label for="expire_date">期限</label>
								<input type="date" class="form-control"	 placeholder="1914/11/01" />
						   <span class="text-danger" >expire_date error</span>
							</div>
							  <div class="form-group form-check">
								<input type="checkbox" class="form-check-input" >
								<label for="finished">完了</label>
							</div>
							<button class="btn btn-primary" type="submit" name="updateOne">登録</button>
						   <button type="button" class="btn btn-default pull-right"><a href="/home">キャンセル</a></button>
						</form>
					   </div>
					 </div>
				 </div>
				<!-- 更新モーダルウィンドウ ここまで-->
				<!-- モーダルウィンドウ ここまで -->
				` ,
 data(){
	return{
		finished_date:'',
					expire_date:'',
					user_id: '',
					item_name:'gest',
	}
	}
 

})

