
$(function(){
/** 画面ロード時の処理. */


  /** モーダル削除ボタンを押したときの処理. */
  $('#btn-delete').click(function (event) {
    let id = event.currentTarget.getAttribute("href").split("/")[2];
    // ユーザー削除
    deleteUser(id);
  });


/** ユーザー削除処理. */
function deleteUser(id) {

  // フォームの値を取得
  var jsonData = {
    id: id
  };
  // ajax通信
  $.ajax({
    type : "post",
    cache : false,
    url : '/delete',
    data: JSON.stringify(jsonData),
    contentType:'application/json',
    dataType : 'json'
  }).done(function(data) {

    // ajax成功時の処理
    // ユーザー一覧画面にリダイレクト
    window.location.href = '/home';

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('ユーザー削除に失敗しました');

  }).always(function() {
    // 常に実行する処理
  });
}
});