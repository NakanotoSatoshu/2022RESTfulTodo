<template>    
        <!-- モーダルウィンドウ外側 -->
		<div class="overlay" id="js-overlay"></div>
		<!-- モーダルウィンドウ外側 -->
		<!-- モーダルウィンドウ 別コンポーネント化VeeValidate化する-->
		<div class="modal  animated  fadeIn" id="js-modal">
			  <div class="modal-close__wrap">
				<button class="modal-close" id="js-close">
					  <span></span>
					  <span></span>
				</button>
			  </div>
			  <br/><br/>
				  <div class="text-center">
					  <h1 id="modalName" class=""></h1>
					  <br/><br/>
					  <h2 class="text-center animated  fadeIn" > を削除します。よろしいですか？</h2>
				  </div>
							<div class="text-center">
								   <button id="btn-delete" class="btn btn-outline-danger" href=""  type="button">削除</button>
								<button type="button" class="btn btn-outline-dark" onclick="history.back();">戻る</button>

							</div>

							<img src="https://www.mcd-holdings.co.jp/news/2011/promotion/img/1111a_2.jpg">
							<img src="https://www.mcd-holdings.co.jp/news/2007/promotion/img/img1003.jpg">
							<img src="https://www.oremichi.com/img_pc/adcar.gif">
		</div>
		<!-- 更新モーダルウィンドウ 別コンポーネント化VeeValidate化する-->
		<div class="modal2  animated  fadeIn" id="js-modal2">
			  <div class="modal-close__wrap">
				<button class="modal-close" id="js-close2">
					  <span></span>
					  <span></span>
				</button>
				
		<div class="form-row align-items-center">
			<form  class="form-inline" action="" method="post" enctype="multipart/form-data">
					 <input class=" shadow-lg  p-3 mb-3 rounded" type="file" name="file"/>
					  <button class="btn btn-outline-dark shadow-lg btn-lg p-3 mb-3 rounded" type="submit" formenctype="multipart/form-data" formmethod="post" formaction="/upload">投稿</button>
			  </form>
		 </div>
		<div class="">
				<form action="" method="post"  >
<!--------------------------------------------------------------------------------- 項目名の入力エリア -->
					<div class="form-group" >
						<label for="item_name">項目名</label>
						<input type="text" class="form-control"  name="input_item_name" v-model="input_item_name" >
						<h5>{{input_item_name}}</h5>
					</div>

<!--------------------------------------------------------------------------------- 担当者の入力エリア -->
					 <div class="form-group" >
						<label for="user_id">担当者</label>
						<select class="form-control" id="os" v-model="os">
							<option   value="" >--------選択してください----------</option>
							<option  >aho</option>
							<option  >aho2</option>
							<option  >aho3</option>
							<option  >aho4</option>
						</select>
						<span class="text-danger" >userId error</span>
						{{os}}
					</div>
					<div class="form-group">
						<label for="expire_date">期限</label>
						<input type="date" class="form-control" placeholder="1914/11/01" >
						
				   <span class="text-danger"  >expire_date error</span>
					</div>
					  <div class="form-group form-check">
						<input type="checkbox" class="form-check-input" v-model="checked">{{checked}}
						<label for="finished">完了</label>
					</div>
				
					<button class="btn btn-primary" type="submit">登録</button>
				   <button type="button" class="btn btn-default pull-right"><a href="/home">キャンセル</a></button>
				</form>
			   </div>
			   <slot><h5>{{ format(iphoneSE3.softbank.date) }}時点での価格</h5>
		<h2>iPhoneSE第3世代64GBメーカー{{ iphoneSE3.softbank.id }}、{{ iphoneSE3.softbank.price }}円</h2></slot>
			 </div>
			 
		 </div>
		<!-- 更新モーダルウィンドウ ここまで-->
		<!-- モーダルウィンドウ ここまで -->
<div class="row">
</div>
</template>