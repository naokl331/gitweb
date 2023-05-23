/**
 * 
 */

 function search(){
	 //alert("search呼ばれた");
	 let data = {
		 "tokuisaki": $("#tokuisaki").val(), //#tokuisaki = jspのid属性  val = value
		 "hurigana": $("#hurigana").val(),
		 "param": "1"
	 }
	 
	 //非同期通信で、サーバー側へ検索を依頼
	 $.post(			//postリクエストを飛ばす
		"customer", //サーバーに"customer"というuliでリクエスト
		data,			//data変数を送る
		function(rtn){
			alert(rtn);
		} 
	 );
	 
 }