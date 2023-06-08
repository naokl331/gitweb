

function searchCustomer(){
	
	let data = $("#searchForm").serialize(); //クエリ文字が保存
	
	$.post(
		"getPages4Customer",
		data,
		function(rtn){
			//alert(rtn);
			if(rtn == 0){
				$("#pagination").twbsPagination('destroy');
				$("#customerList").text("該当するデータはありません");
				return;
			}
			//ページネーション表示
			$("#pagination").twbsPagination('destroy');	//前のページネーション情報を消去する
			$("#pagination").twbsPagination({
				totalPages: rtn,
				visiblePages: 10,
				onPageClick: function(event,page){
					
					getList(data,page);	//リスト取得用functionの呼び出し				
				}
			});
		}
	);
}


 //リスト取得用function
 function getList(data,page){
	 
	 let listdata = data + "&page=" + page;
	 //alert(listdata);
	 
	 $.post(
		 "getList4Customer",
		 listdata,
		 function(rtn){
			 //alert(rtn);
			 $("#customerList").html(rtn);

		 }
	 );
 }
 
 
 //キャンセルボタン押下
 function cancel(){
	if(window.confirm("登録を中断して一覧画面に戻ります。よろしいですか？")){
		window.close();
	} 
 }
 
 
 //得意先登録ボタン押下
 function regist(){
	 //alert("regist() called");
	 
	 //必須チェック
	 if( ($("#tokuisaki_name").val() == "") || ($("#hurigana").val() == "") ){
		 //$("#msg").text("得意先名とﾌﾘｶﾞﾅは必須入力です");
		 alert("必須チェック");
		 return;
	 }
	 
	  //登録確認
	 if(!window.confirm("この内容で登録します。よろしいですか？")){
		 return;
	 }

	 $.post(
		 "updateCustomer",
		 $("#customerEdit").serialize(),
		 function(rtn){
			alert(rtn); 
			//一覧に戻る
			window.close();
		 } 
	);
	 
 }
 
 
 function registNew(){
	 
	 //必須チェック
	 if( ($("#tokuisaki_name").val() == "") || ($("#hurigana").val() == "") ){
		 alert("得意先名とﾌﾘｶﾞﾅは必須入力です");
		 return;
	 }
	 
	  //登録確認
	 if(!window.confirm("この内容で登録します。よろしいですか？")){
		 return;
	 }

	 $.post(
		 "createNew",
		 $("#customerNew").serialize(),
		 function(rtn){
			alert(rtn); 
			//一覧に戻る
			window.close();
		 } 
	);
 }
 
 
 