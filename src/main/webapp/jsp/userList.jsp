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

</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h3 class="text-center">ユーザマスタ 一覧</h3>
	
	<div class="container">
	
		<div class="row">
			<div class="col">
				<form action="user" method="post">
					<label>ID：</label>
					<input type="text" name ="id" value="<%= user.getId() %>">
					<label>名前：</label>
					<input type="text" name ="name" value="<%= user.getName() %>">
					<button type="submit" name="param" value="1">検索</button>
					<button type="submit" name="param" value="2">新規作成</button>
				</form>
			</div>
		</div>
		
		<div class="row">
			<!-- 一覧表示 -->
			<% if(user.getClient().size() > 0){ %>
				<table class="table table-bordered table-stliped">
					<tr>
						<th>ID</th><th>name</th>
					</tr>
					<% for(Map map : user.getClient()){ %>
						<tr>
							<td><%= map.get("id") %></td>
							<td><%= map.get("name") %></td>
							<td>
								<form action="user" method="post">
									<input type="hidden" name="id" value="<%= user.getId() %>">
									<button type="submit" name="param" value="3">編集</button>
								</form>
							</td>
						</tr>
					<% } %>
				</table>
			<% } %>
		</div>
		
	</div>
</body>
</html>