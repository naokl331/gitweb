<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="models.Customer" scope="request"></jsp:useBean>
<%@ page import="java.util.List"  %>
<%@ page import="java.util.Map"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-borderd table-striped">
		<tr>
			<th>得意先No</th>
			<th>得意先名</th>
			<th>フリガナ</th>
			<th>使用フラグ</th>
			<th>編集</th>
		</tr>
		
		<% for(Map map : customer.getList()){ %>
			<tr>
				<td><%= map.get("tokuisaki_no") %></td>
				<td><%= map.get("tokuisaki_name") %></td>
				<td><%= map.get("hurigana") %></td>
				<td>
					<% if(map.get("shiyo_flg").toString().equals("1")){ %>
						可
					<% } else { %>
						不可
					<% } %>
				</td>
				<td>
					<form action="customer" method="post">
						<input type="hidden" name="tokuisaki_no" value="<%= map.get("tokuisaki_no") %>">
						<input type="hidden" name="param" value="">
						<button type="submit" class="btn btn-primary">編集</button>
					</form>
				</td>
			</tr>
		<% } %>
		
	</table>
	
</body>
</html>