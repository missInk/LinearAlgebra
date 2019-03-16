<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线代：注册</title>

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
	
	<!-- Modernizr JS -->
	<script src="${pageContext.request.contextPath }/js/modernizr-2.6.2.min.js"></script>
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="${pageContext.request.contextPath }/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="${pageContext.request.contextPath }/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="${pageContext.request.contextPath }/js/main.js"></script>
	
	<script type="text/javascript">
		$(function(){
			var message0=0,message1=0,message2=0,message3=0,message4=0;
			$("#uid").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUidStyle";
					var args = {"uid":val};
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "学号输入不合法"){
							$("#uidError").text(jsonObject.message + "，请输入8位数字");
							message0=0;
						}else{
							url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUidRegistered";
							$.post(url,args,function(data){
								jsonObject = eval("(" + data + ")");
								if(jsonObject.message == "学号已被注册"){
									$("#uidError").text(jsonObject.message);
									message0=0;
								}else{
									$("#uidError").text("");
									message0=1;
								}
							})
						}
					})
				}
			})
			
			$("#email").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUemailStyle";
					var args = {"uemail":val};
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "邮箱格式不正确"){
							$("#uemailError").text(jsonObject.message);
							message1=0;
						}else{
							url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUemailRegistered";
							$.post(url,args,function(data){
								jsonObject = eval("(" + data + ")");
								if(jsonObject.message == "邮箱已被注册"){
									$("#uemailError").text(jsonObject.message);
									message1=0;
								}else{
									$("#uemailError").text("");
									message1=1;
								}
							})
						}
					})
				}
			})
			
			$("#password").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUpwdStyle";
					var args = {"upwd":val};
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "密码输入不合法"){
							$("#upwdError").text(jsonObject.message+",第一个字必须为字母长度为6~16");
							message2=0;
						}else{
							$("#upwdError").text("");
							message2=1;
							if($.trim($("#re-password").val()) != ""){
								url = "${pageContext.request.contextPath }/SignUpServlet?method=checkTwoUpwdSame";
								args = {"upwd":val,"upwdTwo":$("#re-password").val()};
								$.post(url,args,function(data){
									var jsonObject = eval("(" + data + ")");
									if(jsonObject.message == "两次密码不一致"){
										$("#upwdTwoError").text(jsonObject.message);
										message3=0;
									}else{
										$("#upwdTwoError").text("");
										message3=1;
									}
								})
							}
						}
					})
				}
			})
			
			$("#re-password").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignUpServlet?method=checkTwoUpwdSame";
					var args = {"upwd":$("#password").val(),"upwdTwo":val};
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "两次密码不一致"){
							$("#upwdTwoError").text(jsonObject.message);
							message3=0;
						}else{
							$("#upwdTwoError").text("");
							message3=1;
						}
					})
				}
			})
			
			$("#name").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignUpServlet?method=checkUnameStyle";
					var args = {"uname":val};
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "用户名输入不合法"){
							$("#unameError").text(jsonObject.message);
							message4=0;
						}else{
							$("#unameError").text("");
							message4=1;
						}
					})
				}
			})
			
			$("#submit").click(function(){
				var uname = $("#name").val();
				var uemail = $("#email").val();
				var upwd = $("#password").val();
				var uid = $("#uid").val();
				var url = "${pageContext.request.contextPath }/SignUpServlet?method=signUp";
				var args = {"uname":uname, "uemail":uemail, "upwd":upwd, "uid":uid};
				if(message0==1&&message1==1&&message2==1&&message3==1&&message4==1){
					$.post(url,args,function(data){
						var jsonObject = eval("(" + data + ")");
						if(jsonObject.message == "success"){
							window.location.href="${pageContext.request.contextPath }/sign-in.jsp";
						}else{
							alert(jsonObject.message);
						}
					})
				}else{
					alert("请根据错误提示修改输入内容");
				}
			})
		})
	</script>
	
</head>
<body class="style-2">

		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center"  style="height: 64px;"></div>
			</div>
			<div class="row">
				<div class="col-md-4">
					

					<!-- Start Sign In Form -->
					<form action="" class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
						<h2>Sign Up</h2>
						<div class="form-group">
							<div class="alert alert-success" role="alert">请输入你的信息</div>
						</div>
						<div class="form-group">
							<label for="uid" class="sr-only">StudyId</label>
							<input type="text" class="form-control" id="uid" placeholder="StudyId" autocomplete="off" name="uid">
							<p id="uidError"></p>
						</div>
						<div class="form-group">
							<label for="name" class="sr-only">Name</label>
							<input type="text" class="form-control" id="name" placeholder="Name" autocomplete="off" name="uname">
							<p id="unameError"></p>
						</div>
						<div class="form-group">
							<label for="email" class="sr-only">Email</label>
							<input type="email" class="form-control" id="email" placeholder="Email" autocomplete="off" name="uemail">
							<p id="uemailError"></p>
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" id="password" placeholder="Password" autocomplete="off"  name="upwd">
							<p id="upwdError"></p>
						</div>
						<div class="form-group">
							<label for="re-password" class="sr-only">Re-type Password</label>
							<input type="password" class="form-control" id="re-password" placeholder="Re-type Password" autocomplete="off" name="upwdTwo">
							<p id="upwdTwoError"></p>
						</div>
						<div class="form-group">
							<p>Already registered? <a href="sign-in.jsp">Sign In</a></p>
						</div>
						<div class="form-group">
							<input type="button" id="submit" value="Sign Up" class="btn btn-primary">
						</div>
					</form>
					<!-- END Sign In Form -->
				</div>
			</div>
			<div class="row" style="padding-top: 60px; clear: both;">
				<div class="col-md-12 text-center"><p>蓝点-线性代数学习网站</p></div>
			</div>
		</div>
	

</body>
</html>