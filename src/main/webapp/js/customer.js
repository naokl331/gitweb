/**
 * 
 */

 function search(){
	 //連想配列に画面の入力値を記憶
	 let data = {
		 "tokuisaki": $("#tokuisaki").val(), //画面で入力された得意先  #tokuisaki = jspのid属性  val = value
		 "hurigana": $("#hurigana").val(),	//画面で入力されたフリガナ		$はjQuery
		 "param": "1"									//検索ボタンが押されたことをコントロールに知らせるパラメーター
	 }
	 
	 //非同期通信で、サーバー側へ検索を依頼
	 $.post(				//postリクエストを飛ばす
		"customer",		 //URI(サーバーに"customer"というuliでリクエスト)
		data,				//リクエスト内に持たせるデータ(data変数を送る)　
		function(rtn){	//サーバからのレスポンスを受け取る関数
			if(rtn == 0){
				$("#pagination").twbsPagination('destroy');
				$("#pagination").text("該当するデータはありません");
				return;
			}
			//ページネーション表示
			$("#pagination").twbsPagination('destroy');	//前のページネーション情報を消去する
			$("#pagination").twbsPagination({
				totalPages: rtn,
				visiblePages: 5,
				onPageClick: function(event,page){
					data["page"] = page;	//連想配列にpageを持たせる
					data["param"] = "2";	//リストを要求するリクエスト用
					//リスト取得用functionの呼び出し
					getList(data);
				}
			});
		} 
	 );
 }
 
 
 //リスト取得用function
 function getList(data){
	 //alert(data["param"]);
	 $.post(
		 "customer",
		 data,
		 function(rtn){
			 $("#customerList").html(rtn);
			 //alert(rtn);
		 }
	 )
 }
 
 
 //キャンセルボタン押下
 function cancel(){
	if(window.confirm("登録を中断して一覧画面に戻ります。よろしいですか？")){
		window.close();
	} 
 }
 
 
 //得意先新規登録ボタン押下
 function registNew(){
	 //必須チェック
	 if( ($("#tokuisaki_name").val() == "") && ($("#hurigana").val() == "") ){
		 $("#msg_name").text("必須入力");
		 $("#msg_hurigana").text("必須入力");
		 return;
	 }
	 
	 if( ($("#tokuisaki_name").val() == "") && !($("#hurigana").val() == "") ){
		 $("#msg_name").text("必須入力");
		 $("#msg_hurigana").text("");
		 return;
	 }
	 
	if( !($("#tokuisaki_name").val() == "") && ($("#hurigana").val() == "") ){
		 $("#msg_name").text("");
		 $("#msg_hurigana").text("必須入力");
		 return;
	 }


	 //登録確認
	 if(!window.confirm("この内容で登録します。よろしいですか？")){
		 return;
	 }
	 
	  $.post(
		 "customer",
		 $("form").serialize(),
		 function(rtn){
			if(window.confirm(rtn)){
				//新規登録を続ける場面
				//alert("新規登録をつづける場面");
				//画面をまっさらにしたい
				$("form")[0].reset();
				return;
			}	 
			//一覧に戻る
			window.close();
		 } 
	);
	
 }
 
 
 
 