app.component('todo-list', {
	 props: {todo:{	type:Array },
		   },
     modelValue:{},
     template: `Vue
		<table class="table    table-hover table-sm my-1 iPhoneSE  opaS shadow-lg p-3 mb-3 rounded ">
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
	            <tbody class="animated"> 
	               <tr 
				    v-for="(item,index)  in  todoList"
				   :key="item.user_id"   
				   class="testToggle"
				   :class="{
					'inComp':isExpire(item.finished_date,item.expire_date) ,
					'forwardComp':notExpire(item.finished_date,item.expire_date)
					}">
						<!-- ------------ TODO項目-------------------->
						<!-- ------------ 未完了----------->
						<td class="shadow-lg p-1 mb-1 rounded  align-middle btn-outline-warning modalDel" v-show="this.finished_date !== null" >
						{{item.item_name}}		</td>       <!-- v-show="isNotComp" -->
						<!-- ------------ 完了----------->
						<td class="shadow-lg p-1 mb-1 rounded align-middle btn-lg modalDel"  v-show="this.finished_date === null">{{item.item_name}}</td>
	                   <!-- ------------ 名前-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle modalName" >{{item.user.family_name}}{{item.user.first_name}}
	                    </td>
	                    <!-- ------------ 登録日-------------フォーマットデイト必須------->
	                  <td class="shadow-lg p-1 mb-1 rounded  align-middle iPhoneSE2 modalRegist" >{{format(item.registration_date)}}</td>
	                    <!-- ------------ 期限日-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle iPhoneSE2 modalExpire" >{{format(item.expire_date)}} </td>
	                    <!-- ------------完了日-------------------->
	                    <td class="shadow-lg p-1 mb-1 rounded  align-middle modalFinish"  v-show="hasNull(item.finished_date)">{{format(item.finished_date)}}</td>
	                    <td class="shadow-lg p-3 mb-3 rounded  align-middle medachi2 modalFinish animated fadeIn infinite" v-show="isNull(item.finished_date)">未</td>
						<!-- ------------操作ボタン-------------------->
						<td class="shadow-lg p-1 mb-1 rounded  animated  swing">
							<ul>
							<!-- ------------ 完了系ボタン----------->
							<li class="button animated  swing	">
							
								<button class="shadow-lg p-1 mb-1 rounded btn-complete btn-sm btn-dark"   
								v-on:click="isShow = !isShow" 
								v-bind:href="'/complete/' + item.id" 
								v-show="isNull(item.finished_date)
								">完了</button>
								<button class="shadow-lg p-1 mb-1 rounded btn-incomplete btn-sm btn-outline-dark" 
								v-on:click="isShow = !isShow" 
								v-bind:href="'/incomplete/' + item.id" 
								v-show="hasNull(item.finished_date)
								">未完了</button>
								
							</li>
							<!-- ------------ 未完了だった箇所----------->
							<li class="  animated  fadeIn" >
								
							</li>
							<li><!-- ------------ 更新画面----------->
	                           <button class="shadow-lg p-1 mb-1 rounded btn-sm btn-dark modal-open2"   v-bind:href="'/edit/' + item.id" >更新</button>
	                         </li>
	                         <li><!-- ------------ 削除画面----------->
	                  		  <button id="js-open" class="shadow-lg p-1 mb-1 rounded btn-sm btn-dark  modal-open"   type="button" v-bind:href="'/delete/' + item.id">削除</button>
	                  		</li>
	                  		</ul>
						</td>
	                </tr>
	            </tbody>
	        </table>` ,
	 data() {
	          return {
					os:[],
					checked:'',
					input_item_name:'gest',
					user_id: '',
					//complete:'',
					toggle: true,
					//isExpire:true,
					//notExpire:true,
					finished_date:'',
					expire_date:'',
					//Swicth:false,
	              	details: [],
					number: [     { },{  },   ],
	              	iphoneSE3:{
							softbank:{
							id:'softbank' ,
							price:73440, 
							date:2022/12/8,
							},
							

						au:{id:'au',  price:70935,  date:2022/12/8},
						docomo:{id:'docomo',price:73370,date:2022/12/8},
						//{id:ahamo,price:73370,date:2022/12/8},
						//{id:ocn,price:48950,date:2022/12/8},
						//{id:uq,price:48935,date:2022/12/8},
						//{id:ocnused,price:35310,date:2022/12/8},
						//{id:iijimo,price:38800,date:2022/12/8},
						//{id:ymobile,price:55440,date:2022/12/8}
						},
				    user:{
						firstName: 'John',
						lastName: 'Doe',
						age: '25',
						sex: '男性',
					  },
	          	 }
	        },
	        methods:{
				// postEdit:(    ) => {
				//	axios.post('/...',{
				//		   name: this.name
				//		  })
				//		 .then(response => this.users.unshift(response.data))
				//		 .catch(error => console.log(error))     
				//   } ,
					//onSubmit() {
					//let productReview = {
					 // name: this.name,
					 // review: this.review,
					 // rating: this.rating,
					//}
					//this.$emit('review-submitted', productReview)
			   
					//this.name = ''
					//this.review = ''
					//this.rating = null
				 // },
				onChange: (checked) =>{
					console.log(checked)

					this.modelValue

					this.$emit('input',modelValue)

					this.modelValue = ''
				   
				},
				isInvalidDate:function(){
					var da = new Date
					return this.todo.filter(function(item){
							    if(item.finished_date === null){
								Number(item.finished_date)
								console.log(item.finished_date)
							    }else{
                                return item.finished_date
								console.log(item.finished_date)
								}
						}
					)},
					//完了日がぬるつまり未完了
				isNull:(d) => {
					
					if(null === d){
						//Number(d)
						//console.log('未完了' + d)
						return true
					}else{
						//console.log('ぬるぽでない' )
						return false
					}

				},
				//完了日があるつまり完了してるやつ
				hasNull:(d) => {
					if(null === d){
						return false
					}else{
						//console.log('完了' + d  + Number(d))
						return true
					}

				},
				isExpire:(f,e)=>{
					var d = new Date
					//return this.todo.filter(function(item){
					
					if(f === null){
						 if(new Date(e) < d) {
							return true
						 }else{
							return false
						 }
					}
					//console.log("test")
					return false
					//})
				},
				notExpire:(f,e)=>{
					var d = new Date
					//return this.todo.filter(function(item){
					//let bl = this.finished_date === null
					//let bl = f === null
					if(f === null){
						// if(Date(item.expire_date) >= d) {
							if(new Date(e) > d){
							return true
						 }else{
							return false
						 }
						}
						return false
					//})
				},
				//toggleBtn: function(){
				//	this.toggle == true ? this.toggle = false : this.toggle = true
				//},
				//Swicth:(e)=>{
				//	return false
				//},
				//isInvalidDate:()=>{

				//},

				format(date) {
				   let created_at = dayjs(date).format('YYYY年M月DD日')
				  return created_at
				}
				

			},
	        computed: {
				todoList:  function(){
				  return this.todo.filter(function (item) {
					return   item.is_deleted === 0
				  })
				}
			}
})


