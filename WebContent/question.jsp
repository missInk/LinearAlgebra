<%@page import="java.util.*"%>
<%@page import="lxx.linearAlgebra.entity.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题详情</title>
<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<!-- SET: STYLESHEET -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/question-css.css">
<!-- END: STYLESHEET -->
</head>
<body>

<!-- 问题内容 -->
<h3 id="title">${topic.getTitle() }</h3>
<p id="content">${topic.getContent() }</p>
<br/><br/><br/><br/>

<%
List<Comment> comments = (ArrayList<Comment>)request.getAttribute("comments");
for(int i = 0; i < comments.size(); i++){
%>
<p><%=comments.get(i).getComment_user_id() %></p>
<p><%=comments.get(i).getContent() %></p>
<p><%=comments.get(i).getComment_time() %></p>
<%
}
%>
<!-- 发表评价 -->
<!--
点击提交评价，将表单上传给服务器，之后再返回到本界面中
服务器需要的数据：用户id,问题id，评价内容，
用户楼层获取方法：
    1、获取所有该问题的评论，并按照楼层从大到小进行排序
    2、该用户的楼层就是所以评论数目加一
用户状态：
1：正常
0：被隐藏（删除）/SignUpServlet?method=checkUidStyle
-->
<form action="QuestionServlet?method=upComment" method="post">
	<input type="hidden" value="${topic.getIdtopic() }" name="idtopic">
    <textarea cols="40" rows="8" name="content"></textarea>
    <input type="submit" value="提交评价">
</form>

</body>
</html>