/**
 * ユーザマスタメンテ用JSファイル
 */

 function cancel(){
	if(window.confirm("登録を中断し一覧へ戻ります。よろしいですか？")){
	window.location.href="user";
	}
 }

 function test(){
	//パスワードとパスワード(確認用)が一致しているのか関連チェック
 	let pass = document.getElementById("pass").value;
	alert(pass);
	
	document.getElementById("name").value = "木村拓哉";
 }
