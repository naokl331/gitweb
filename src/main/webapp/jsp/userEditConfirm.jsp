<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="models.User" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー新規登録</title>
<script type="text/javascript" src="js/user.js"></script>
<style>
.form_bg{
	background-color: #b2ffd8;
}
.btn-pos{
	float: right;
	margin: 20px 10px 10px 10px;
}

.btn1{
	
}
.btn2{
	margin-left: 10px;
}
.nametag{
	margin-left: 10px;
}

.error_msg{
	background-color: #ef857d;
	padding:10px 0;
}

</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<h3 class="logo text-center mt-3">ユーザー登録確認</h3>
	<h4 class="text-center mt-3 text-warning">この内容で登録しますか？</h4>
	
	<div class="container">
	
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 justify-content-center form_bg my-4 p-3 rounded">
				<form action="user" name="userForm" method="post">
					<label class="form-label">ID</label>
					<input class="form-control" type="text" name ="id" id="id" placeholder="ID" value="<%= user.getId() %>" <%= user.getReadonly() %> readonly>
					<label class="form-label">名前</label>
					<input class="form-control" type="text" name ="name" id="name" placeholder="名前" value="<%= user.getName() %>" readonly>
					<label class="form-label">パスワード</label>
					<input class="form-control" type="password" name ="pass" placeholder="パスワード" value="<%= user.getPass() %>" readonly>
					<div class="btn-pos">
						<button type="submit" name="param" value="4" class="btn btn-secondary btn-sm btn1">登録</button>
						<button type="submit" name="param" value="5" class="btn btn-secondary btn-sm btn2">キャンセル</button>
					</div>
				</form>
			</div>
			<div class="col-3"></div>
		</div>
		
	</div>
	
</body>
</html>

