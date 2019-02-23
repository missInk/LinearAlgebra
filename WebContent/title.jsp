<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Header Starts -->
<div class="header">
  <div class="header-container">
    <div class="logo"> <a href="${pageContext.request.contextPath }/index.jsp">线代学习</a> </div>
    <div class="toll-free">
    <%if(session.getAttribute("uid") == null){ %>
      <a href="${pageContext.request.contextPath }/sign-in.jsp">登录</a>
      <a href="${pageContext.request.contextPath }/sign-up.jsp">注册</a>
    <%}else{ %>
	  <a>${uname}</a>
	  <a href="javascript:void(0);" onclick="signOut()" style="color:red">安全退出</a>
    <%} %>
    </div>
    <div class="clear"></div>
  </div>
  
	<script type="text/javascript">
		function signOut(){
			var url = "${pageContext.request.contextPath }/SignInServlet?method=signOut";
			var args = {};
			$.get(url,args,function(data){
				$(".header").html(data);
			});
		}
	</script>
  
</div>
<!-- Header ends --> 