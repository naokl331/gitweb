<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="menu" class="models.Menu" scope="request"></jsp:useBean>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>見積・納品・請求管理</title>
<style type="text/css">
body{background: linear-gradient(90deg, rgb(22, 135, 237), rgb(20, 55, 90));}

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
.master-meintenance{background-color:#BAE3F9;}
.business{background-color: #E6C0BD;}
.system{background-color: #BDE1D6;}
.container{background-color: grey;}

h5 {
  color: #364e96;
  border: solid 3px #364e96;
  padding: 0.2em;
  border-radius: 0.5em;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<h3 class="text-center logo mt-4">メインメニュー</h3>
	
	<div class="container w-75">
	
		<div class="row mt-4">
		
			<!-- マスタメンテ -->
			<div class="col master-meintenance m-1">
				<!--  -->
				<h5 class="text-center mt-4 mb-3">マスタメンテ</h5>
				
				<!-- リスト -->
				<ul>
					<% for(Map map : menu.getMastamente()){ %>
						<li> <a href="<% map.get("url"); %>"><%= map.get("menu_name").toString() %></a></li>
					<% }%>
				</ul>
			</div>
			
			<!-- 業務 -->
			<div class="col business m-1">
				<h5 class="text-center mt-4 mb-3">業務</h5>
				
				<!-- リスト -->
				<ul>
					<% for(Map map : menu.getBusiness()){ %>
						<li> <a href="<% map.get("url"); %>"><%= map.get("menu_name").toString() %></a></li>
					<% }%>
				</ul>
			</div>
			
			<!-- システム -->
			<div class="col system m-1">
				<h5 class="text-center mt-4 mb-3">システム</h5>
				
				<!-- リスト -->
				<ul>
					<% for(Map map : menu.getSystem()){ %>
						<li> <a href="<% map.get("url"); %>"><%= map.get("menu_name").toString() %></a></li>
					<% }%>
				</ul>
			</div>
		
		</div>
	
	</div>
	
</body>
</html>