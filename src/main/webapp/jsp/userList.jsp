<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="models.User" scope="request"></jsp:useBean>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h3 class="logo text-center mt-3">ユーザマスタ 一覧</h3>
	
	<div class="container">
	
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6 justify-content-center form_bg my-4 p-3 rounded">
				<form action="user" name="userForm" method="post">
					<!-- <label><strong>ID：</strong></label> -->
					<input type="text" name ="id" placeholder="ID" value="<%= user.getId() %>" class="in">
					<!-- <label class="nametag"><strong>名前：</strong></label> -->
					<input type="text" name ="name" placeholder="名前" value="<%= user.getName() %>" class="in">
					<button type="submit" name="param" value="1" class="btn btn-secondary btn-sm btn1">検索</button>
					<button type="submit" name="param" value="2" class="btn btn-secondary btn-sm btn2">新規作成</button>
				</form>
			</div>
			<div class="col-3"></div>
		</div>
		
		<!-- 一覧表示 -->
		<div class="row">
		
			<div class="col"></div>
			<div class="col justify-content-center">
				<% if(user.getClient().size() > 0){ %>
					<table class="table table-bordered table-striped table-light">
						<tr class="text-center">
							<th>ID</th><th>name</th><th>編集</th>
						</tr>
						<% for(Map map : user.getClient()){ %>
							<tr>
								<td class="text-center"><%= map.get("id") %></td>
								<td class="text-center"><%= map.get("name") %></td>
								<td class="text-center">
									<form action="user" method="post">
										<input type="hidden" name="id" value="<%= map.get("id") %>">
										<button type="submit" name="param" value="6" class="btn btn-outline-primary btn-sm">編集</button>
									</form>
								</td>
							</tr>
						<% } %>
					</table>
				<% } %>
			</div>
			<div class="col"></div>
		</div>
		
	</div>
</body>
</html>