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
<style>
.logo{
	position: relative;
  	padding: 0.5em;
	background: #a6d3c8;
  	color: white;
  	margin: 0 35%;
  	}
.logo::before {
  position: absolute;
  content: '';
  top: 100%;
  left: 0;
  border: none;
  border-bottom: solid 15px transparent;
  border-right: solid 20px rgb(149, 158, 155);
}

.form_bg{
	background-color: #b2ffd8;
}

.btn1{
	margin-left: 10px;
	margin-right: 10px;
}
.btn2{
	margin-right: 10px;
}
.nametag{
	margin-left: 10px;
}

.in{
	margin-left: 20px;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<h3 class="logo text-center mt-3">得意先マスタ一覧</h3>
	
	<div class="container">
	
		<div class="row justify-content-center">
			<div class="col-8 justify-content-center form_bg my-4 p-3 rounded">
				<label>得意先：</label>
				<input type="text" id="tokuisaki" placeholder="得意先" value="" class="in">
				<label class="nametag">フリガナ：</label>
				<input type="text" id="furigana" placeholder="フリガナ" value="" class="in">
				<button type="button" class="btn btn-secondary btn-sm btn1" onclick="search()">検索</button>
				<button type="button" class="btn btn-secondary btn-sm btn2" onclick="">新規作成</button>
			</div>
		</div>
		
	</div>
</body>
</html>