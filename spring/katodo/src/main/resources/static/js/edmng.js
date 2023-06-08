


//読み込み時に自動起動
$(function(){
	
	let searchFlg = $("#searchFlg").val();
	//alert(searchFlg);
	
	if(searchFlg == 1) searchEdmng();
	
});


function searchEdmng(){
	
	$("#searchFlg").val(1);
	let data = $("#searchForm").serialize();
	
	$.post(
		"getPages4Edmng",
		data,
		function(rtn){
			alert(rtn);
			
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
	)
	
}

//リスト取得用function
 function getList(data,page){
	 
	 let listdata = data + "&page=" + page;
	 //alert(listdata);
	 
	 $.post(
		 "getList4Edmng",
		 listdata,
		 function(rtn){
			 //alert(rtn);
			 $("#edmng").html(rtn);

		 }
	 );
 }
 