<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="js/customer.js"></script>
<link rel="stylesheet" href="css/search.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<h3 class="logo text-center mt-3">得意先マスタ一覧</h3>
	
	<div class="container">
	
		<div class="row justify-content-center">
			<div class="col-8 justify-content-center form_bg my-4 p-3 rounded">
				<label style="float:left;">得意先：</label>
				<input type="text" id="tokuisaki" placeholder="得意先" value="" class="in" style="float:left;">
				<label class="nametag" style="float:left;">フリガナ：</label>
				<input type="text" id="hurigana" placeholder="フリガナ" value="" class="in"style="float:left;">
				
				<form action="customer" method="post" target="_blank"> <!-- target="_blank"で別タブで開くようになる -->
					<button type="button" class="btn btn-secondary btn-sm btn1" onclick="search()" style="float:left;">検索</button>
					<input type="hidden" name="param" value="3">
					<button type="submit" class="btn btn-secondary btn-sm btn2" style="float:left;">新規作成</button>
				</form>
			</div>
		</div>
		
		<div class = "row justify-content-center">
   			<nav aria-label="Page navigation" class="col-6">
        		<ul class="pagination" id="pagination"></ul>
    		</nav>
		</div>
		<div class="row justify-content-center>
			<div class="col" id="customerList"></div>
		</div>
		
	</div>
</body>
</html>