<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动解答模块</title>
<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<!-- SET: STYLESHEET -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/AutomaticAnswer-css.css">
<!-- END: STYLESHEET -->

<script type="text/javascript">
function changeMain(jsp_name){
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=goPage&pageName="+jsp_name;
	var args = {};
	$.post(url,args,function(data){
		$(".main").html(data);
	})
}
</script>

</head>
<body>

<div class="container">
   <!-- 左侧导航栏开始 -->
    <div>
        <a href="javascript:void(0);" onclick="changeMain('getValue.jsp')">行列式求值</a>
        <a href="javascript:void(0);" onclick="changeMain('multiplication.jsp')">解线性方程组</a>
        <a href="javascript:void(0);" onclick="changeMain('multiplication.jsp')">矩阵加法和乘法</a>
        <a href="javascript:void(0);" onclick="changeMain('transpose.jsp')">矩阵的转置</a>
        <a href="javascript:void(0);" onclick="changeMain('mrinv.jsp')">实现逆矩阵</a>
        <a href="javascript:void(0);" onclick="changeMain('rank.jsp')">求矩阵的秩</a>
        <a href="#">线性相关的判断</a>
        <a href="#">矩阵的特征值</a>
        <a href="#">判断两个矩阵是否相似</a>
    </div>
    <!-- 左侧导航栏结束 -->

    <!-- 内容开始 -->
    <div class="main">
        <%@ include file="automaticAnswer/getValue.jsp"%>
    <!-- 内容结束 -->
    </div>
</div>
</body>
</html>