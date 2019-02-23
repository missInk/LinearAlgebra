<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线代：登录</title>

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
			$("#uid").change(function(){
				var val = $(this).val();
				val = $.trim(val);
				if(val != ""){
					var url = "${pageContext.request.contextPath }/SignInServlet?method=checkUid";
					var args = {"uid":val};
					$.post(url,args,function(data){
						$("#signinError").text(data);
					})
				}
			})
			$("#submit").click(function(){
				var uid = $("#uid").val();
				var upwd = $("#password").val();
				var url = "${pageContext.request.contextPath }/SignInServlet?method=signIn";
				var args = {"uid":uid, "upwd":upwd};
				$.post(url,args,function(data){
					var jsonObject = eval("(" + data + ")");
					if(jsonObject.message == "success"){
						history.go(-1);
					}else{
						$("#signinError").text(jsonObject.message);
					}
				})
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
					<form action="" class="fh5co-form animate-box" data-animate-effect="fadeInLeft" method="post">
						<h2>Sign In</h2>
						<div id="signinError">
							
						</div>
						<div class="form-group">
							<label for="StudyId" class="sr-only">StudyId</label>
							<input type="text" class="form-control" id="uid" placeholder="StudyId" autocomplete="off" name="uid">
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<input type="password" class="form-control" id="password" placeholder="Password" autocomplete="off" name="upwd">
						</div>
						<div class="form-group">
							<label for="remember"><input type="checkbox" id="remember"> Remember Me</label>
						</div>
						<div class="form-group">
							<p>Not registered? <a href="sign-up.jsp">Sign Up</a> | <a href="forgot.jsp">Forgot Password?</a></p>
						</div>
						<div class="form-group">
							<input type="button" value="Sign In" class="btn btn-primary" id="submit">
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