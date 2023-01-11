app.component('vue-display', {
	 props: {
		    isByVue: {
		      type: Boolean,
		      required: true
		    },
		    todoItem : todo
	 },
  template:
    /*html*/
	    `<table class="table    table-hover table-sm my-1 iPhoneSE  opaS shadow-lg p-3 mb-3 rounded ">
	            <thead class="thead">
	                <tr>
	                    <th class=" col-sm-2">項目名</th>
	                    <th class=" col-sm-2">担当者</th>
	        		     <th class=" col-sm-2 iPhoneSE2">登録日</th>
	                    <th class=" col-sm-2 iPhoneSE2">期限日</th>
	                    <th class=" col-sm-2">完了日</th>
	                    <th class=" col-sm-2" colspan="3" >操作</th>
	                </tr>
	            </thead>

	            <tbody class="animated  ">
	            	<!-------------- タイムリー府君------------------Vueに全て変更-->
	               <tr v-for="(item,i) in ${todo}  "  :key="i">
	               <!-- これがVUEになるとこうなる
	                <tr class="inComp active"  :class="{active : activeClass}">-->
						<!-- ------------ TODO項目-------------------->
						<!-- ------------ 未完了----------->
						<td class="shadow-lg p-1 mb-1 rounded  align-middle btn-outline-warning modalDel"  ></td>
						<!-- ------------ 完了----------->
						<td class="shadow-lg p-1 mb-1 rounded align-middle btn-lg modalDel"  ></td>
	                   <!-- ------------ 名前-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle " >{{item.name}}
	                    <p  />アイテムが見つかりました！p> </td>
	                    <!-- ------------ 登録日-------------------->
	                  　<td class="shadow-lg p-1 mb-1 rounded  align-middle iPhoneSE2" ></td>
	                    <!-- ------------ 期限日-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle iPhoneSE2" > </td>
	                    <!-- ------------完了日-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle" ></td>
	                    <td class="shadow-lg p-3 mb-3 rounded  align-middle medachi2 animated fadeIn infinite" ></td>
						<!-- ------------操作ボタン-------------------->
						<td class="shadow-lg p-1 mb-1 rounded  animated  swing">
							<ul>
							<!-- ------------ 完了----------->
							<li class="button animated  swing	" ">
								<button  class="shadow-lg p-1 mb-1 rounded btn-complete btn-sm btn-dark "  >完了</button>
							</li>
							<!-- ------------ 未完了----------->
							<li class="  animated  fadeIn" >
								<button class=" shadow-lg p-1 mb-1 rounded btn-incomplete btn-sm btn-outline-dark" >未完了</button>
							</li>
							<li><!-- ------------ 更新画面----------->
	                           <button class="shadow-lg p-1 mb-1 rounded btn-sm btn-dark modal-open2"  >更新</button>
	                         </li>
	                         <li><!-- ------------ 削除画面----------->
	                  		  <button id="js-open" class="shadow-lg p-1 mb-1 rounded btn-sm btn-dark  modal-open"   type="button">削除</button>
	                  		</li>
	                  		</ul>
						</td>
	                </tr>
	            </tbody>
	        </table>`
	     ,
	 data() {
	          return {

	              selectedVariant: 0,
	              details: ['50% cotton', '30% wool', '20% polyester'],
	              variants: [
	                { id: 2234, color: 'green', image: './assets/images/socks_green.jpg', quantity: 50 },
	                { id: 2235, color: 'blue', image: './assets/images/socks_blue.jpg', quantity: 0 },
	              ]
	          }
	        },
	        methods: { },
	        computed: { }
	})
