<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="login" class="models.Login" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>見積・納品・請求管理</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"> -->
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<div class="container justify-">
		<div class="row">
			<div class="col">
				<h1 class="text-center mt-5">加藤堂経師店見積・納品・請求管理システム</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h3 class="text-center mt-2">ログイン</h3>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col"></div>
			<div class="col bg-info">
				<form action="login" method="post">
					<div class="row">
						<label>ID:</label>
					</div>
					<div class="row mt-2 mx-1">
						<input type="text" name="id" value="<%= login.getId() %>">
					</div>
					<div class="row mt-3 mx-1">
						<label>PASS:</label>
					</div>
					<div class="row mt-2 mx-1">
						<input type="password" name="pass" value="<%= login.getPass() %>">
					</div>
					<div class="row mt-3 mx-2">
						<button type="submit" class="btn btn-primary">ログイン</button>
					</div>
					<div class="row mt-2 mx-1">
						<%=login.getMsg() %>
					</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
		
	</div>
</body>
</html>