//今後モーダルもVueで部品化せねばいけない
$(function(){
  // 変数に要素を入れる
  var open = $('.modal-open'),
  open2 = $('.modal-open2'),
    close = $('#js-close'),
    close2 = $('#js-close2'),
    overlay = $("#js-overlay"),
    container = $('#js-modal'),
    container2 = $('#js-modal2'),
    editArray = [];
  	

   /*  modalName = $('.modal').children('h1')*/

  //開くボタンをクリックしたらモーダルを表示する
  open.on('click',function(){
	tdName = $(this).closest('tr').children("td.modalDel") ;
	tdName = tdName.text();
	$('#modalName').text(tdName);
	del = $(this).attr('href');
	$('#btn-delete').attr('href',del);
    container.addClass('open');
    overlay.addClass("open");
    return false;
  });
  //更新ボタンをクリックしたら更新モーダルを表示する
  open2.on('click',function(){
	editVar = $(this).closest('tr').children("td.modalDel") ;
	editVar = editVar.text();
  editArray.push(editVar);
  editVar = $(this).closest('tr').children("td.modalName") ;
	editVar = editVar.text();
  editArray.push(editVar);
  editVar = $(this).closest('tr').children("td.modalRegist") ;
	editVar = editVar.text();
  editArray.push(editVar);
  editVar = $(this).closest('tr').children("td.modalExpire") ;
	editVar = editVar.text();
  editArray.push(editVar);
  editVar = $(this).closest('tr').children("td.modalFinish") ;
	editVar = editVar.text();
  editArray.push(editVar);
	$('#modalName2').text(editArray);
  document.getElementById( "item_name" ).value = editArray[0];
  //document.getElementById( "user_id" ).firstElementChild.value = editArray[1]
  document.getElementById( "user_id" ).querySelector(
    "option[value='"+editArray[1]+"']");
  document.getElementById( "user_id" ).querySelector("option").textContent = editArray[1]
    //.setAttribute("selected","selected");
  var t2 = new Date();
  t2 = editArray.splice(2,1);
  t3 = t2[0].replace("年","-").replace("月","-").replace("日","");
  document.getElementById( "expire_date" ).value = t3;
  //isFinishedCheck(editArray);
   // if(  value === "undefined" ){ 

    //  };
	edit = $(this).attr('href');
	$('#btn-delete2').attr('href',edit);  
    container2.addClass('open');
    overlay.addClass("open");
    return false;
  });
  //閉じるボタンをクリックしたらモーダルを閉じる
  close.on('click',function(){
    container.removeClass('open');
    overlay.removeClass("open");
  });
  close2.on('click',function(){
	    container2.removeClass('open');
	    overlay.removeClass("open");
	  });

  //モーダルの外側をクリックしたらモーダルを閉じる
  $(document).on('click',function(e) {
    if(!$(e.target).closest('.modal-body').length) {
      container.removeClass('open');
      overlay.removeClass("open");
    }
  });
  //更新モーダルの外側をクリックしたらモーダルを閉じる
  $(document).on('click',function(e) {
    if(!$(e.target).closest('.modal-body').length) {
      /*container2.removeClass('open');*/
      overlay.removeClass("open");
    }
  });
});

//function isFinishedCheck(editArray)
    //  if($('#').length){
     // };
  
  
  
/*
 *   var tdName = $(this).closest('tr').children("td.test").val();
var name = tdName.value;
var modalName = $('.modal').children('h1').append(tdName);
modalName.value = name;

$('.modal-open').on('click',function(){
	var tdName = $(this).closest('tr').children("td.test").val()
     });
	tdName.text();
	 */