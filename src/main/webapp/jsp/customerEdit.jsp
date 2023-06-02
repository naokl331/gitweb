<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="models.Customer" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/search.css">
<script type="text/javascript" src="js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="js/customer.js"></script>
<style>
.btn-pos{
	float: right;
	margin: 20px 10px 10px 10px;
}
</style>
</head>
<body>
	<jsp:include page="header2.jsp"></jsp:include>
	
	<h3 class="text-center logo mt-3"><%= customer.getHeader() %></h3>
	
	<div class="container justify-content-center">
		<div class="row">
			<div class="col-2"></div>
			
			<div class="col-8 justify-content-center form_bg my-4 p-3 rounded">
			
				<form id="customerEdit">
				
					<input type="hidden" name="param" value="4">
					
					<div class="row">
						<div class="col-6">
							<label class="form-label">得意先名</label> <div id="msg_name" style="color: red; display: inline;"></div>
							<input class="form-control" type="text" name="tokuisaki_name" id="tokuisaki_name" value="<%= customer.getTokuisaki_name() %>" placeholder="得意先名" required>
							
							<label class="form-label">郵便番号(ハイフンなし)</label>
							<input class="form-control" type="text" name="yubin_bng" id="yubin_bng" value="<%= customer.getYubin_bng() %>" placeholder="郵便番号">
						
						</div>
						
						<div class="col-6">
							<label class="form-label">フリガナ(半角)</label> <div id="msg_hurigana" style="color: red; display: inline;"></div>
							<input class="form-control" type="text" name="hurigana" id="hurigana" value="<%= customer.getHurigana() %>" placeholder="ﾌﾘｶﾞﾅ" required>
					
							<label class="form-label">得意先番号</label>
							<input class="form-control" type="text" name="tokuisaki_no" value="<%= customer.getTokuisaki_no() %>" placeholder="No" readonly>
					
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<label class="form-label">住所１</label>
							<input class="form-control" type="text" name="address1" id="address1" value="<%= customer.getAddress1() %>" placeholder="都道府県・市区町村">
					
							<label class="form-label">住所２</label>
							<input class="form-control" type="text" name="address2" id="address2" value="<%= customer.getAddress2() %>" placeholder="番地 建物名・部屋番号">
						</div>
					</div>
					
					<div class="row">
						<div class="col-6">
							<label class="form-label">担当</label>
							<input class="form-control" type="text" name="tanto" id="tanto" value="<%= customer.getTanto() %>" placeholder="担当">
						</div>
						
						<div class="col-6">
							<label class="form-label">使用フラグ</label>
							<select class="form-control" name="shiyo_flg" value="<%= customer.getShiyo_flg() %>" placeholder="使用フラグ">
							<option value="1">可</option>
							<option value="2">不可</option>
							</select>
						</div>
					</div>
					
					<div class="btn-pos">
						<button type="button" onclick="registNew()" class="btn btn-secondary btn-sm btn1">登録</button>
						<button type="button" onclick="cancel()" class="btn btn-secondary btn-sm btn2">キャンセル</button>
					</div>
					
				</form>
				
			</div>	
		</div>
	</div>
	
</body>
</html>