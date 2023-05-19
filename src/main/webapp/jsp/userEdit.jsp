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
	
	<h3 class="logo text-center mt-3"><%= user.getHeadline() %></h3>
	
	<div class="container">
	
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 justify-content-center form_bg my-4 p-3 rounded">
				<form action="user" name="userForm" method="post">
					<label class="form-label">ID</label>
					<input class="form-control" type="text" name ="id" id="id" placeholder="ID" value="<%= user.getId() %>" <%= user.getReadonly() %> required>
					<label class="form-label">名前</label>
					<input class="form-control" type="text" name ="name" id="name" placeholder="名前" value="<%= user.getName() %>" required>
					<label class="form-label">パスワード</label>
					<input class="form-control" type="password" name ="pass" id="pass" placeholder="パスワード" value="<%= user.getPass() %>" required>
					<label class="form-label">パスワード(確認)</label>
					<input class="form-control" type="password" name="pass2" id="pass2" placeholder="パスワード(確認)" class="form-control" value="" required>
					<div class="btn-pos">
						<button type="submit" name="param" value="<%= user.getParam() %>" class="btn btn-secondary btn-sm btn1">登録</button>
						<button type="button" onclick="cancel()" class="btn btn-secondary btn-sm btn2">キャンセル</button>
					</div>
				</form>
				
				
			</div>
			<div class="col-3"></div>
		</div>
		
		<div class="row">
			<div class="col-3"></div>
			<% if(!(user.getMsg() == "")){ %>
				<div class="col-6 text-center error_msg rounded">
				<%= user.getMsg() %>
				</div>
			<% } %>
			<div class="col-3"></div>
		</div>
		
	</div>
	
</body>
</html>

