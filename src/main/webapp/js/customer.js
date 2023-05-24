/**
 * 
 */

 function search(){
	 //alert("search呼ばれた");
	 //連想配列に画面の入力値を記憶
	 let data = {
		 "tokuisaki": $("#tokuisaki").val(), //画面で入力された得意先  #tokuisaki = jspのid属性  val = value
		 "hurigana": $("#hurigana").val(),	//画面で入力されたフリガナ
		 "param": "1"									//検索ボタンが押されたことをコントロールに知らせるパラメーター
	 }
	 
	 //非同期通信で、サーバー側へ検索を依頼
	 $.post(				//postリクエストを飛ばす
		"customer",		 //URI(サーバーに"customer"というuliでリクエスト)
		data,				//リクエスト内に持たせるデータ(data変数を送る)　
		function(rtn){	//サーバからのレスポンスを受け取る関数
			//alert(rtn);	
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
					//alert(page);
					//連想配列にpageを持たせる
					data["page"] = page;
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
			 alert(rtn);
		 }
	 )
 }