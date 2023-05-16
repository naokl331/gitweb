<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>見積・納品・請求管理</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<div class="container-fluid bg-dark text-white">
	
		<div class="row">
			<h3 class="text-center mt-3">見積・納品・請求管理システム</h3>
		</div>
		
		<div class="row">
				<div class="col mb-3">
					<p class="text-end">
						<label>年度</label>
						<input type="text" value="<%= session.getAttribute("nendo") %>" readonly>
						<label>ログイン：<%= session.getAttribute("name") %></label>
						<a href="#" class="link-warning">メニューへ戻る</a>
					</p>
				</div>
		</div>
		
	</div>

</body>
</html>