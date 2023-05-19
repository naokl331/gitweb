<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container">
	
		<div class="row">
			<h4 class="text-center">ユーザの登録が完了しました。</h4>
		</div>
		<div class="row">
			<div class="col"></div>
			<div class="col">
				<ul>
				<li>
					<form action="user" method="post">
						<button class="btn btn-link" type="submit" name="param" value="2">新規登録を続ける</button>
					</form>
					</li>
					<li>
						<form action="user">
							<button class="btn btn-link" type="submit">ユーザー一覧に戻る</button>
						</form>
					</li>
				</ul>
			</div>
			<div class="col"></div>
		</div>
	</div>
</body>
</html>