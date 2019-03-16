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
<script type="text/javascript">
$(document).ready(function(){
	$("#submitComment").off("click");
})
</script>
</head>
<body>
<%@ include file="title.jsp"%>

<!-- 问题内容 -->
<div id="body" class="container">
    <div class="layout-wrap">
        <div class="wgt-ask accuse-response line " id="wgt-ask">
            <h1 accuse="qTitle">
                <span class="ask-title">${topic.getTitle() }</span>
            </h1>
            <div class="line mt-5 q-content" accuse="qContent" style="visibility: visible; max-height: none;">
                <span class="con conReal">${topic.getContent() }</span>
            </div>
            <div class="line f-aid ask-info ff-arial" id="ask-info">
                <div class="cannotanswer-alias-wp">
                    <span id="v-times" style="display: inline;">${topic.getTopic_time() }</span>
                </div>
            </div>
            <div class="question-all-answers-number">
                <span class="question-all-answers-line"></span>
                <span class="question-all-answers-title" style="font-weight: 700;">回答</span>
            </div>
            <%
			List<Comment> comments = (ArrayList<Comment>)request.getAttribute("comments");
			for(int i = 0; i < comments.size(); i++){
			%>
            <div class="wgt-best">
                <div class="wgt-replyer-all">
                    <span class="wgt-replyer-all-uname"><%=comments.get(i).getComment_user_id() %></span>
                    <span class="wgt-replyer-all-box"><span class="wgt-replyer-all-time">回答于<%=comments.get(i).getComment_time() %></span></span>
                </div>
                <div class="bd answer">
                    <div class="line content">
                        <div accuse="aContent" class="best-text mb-10 context">
                            <%=comments.get(i).getContent() %>
                        </div>
                    </div>
                    <img src="${pageContext.request.contextPath }/images/line.png" class="bottom-dashed-line" style="width: 1010px;">
                </div>
            </div>
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
			<div class="question-all-answers-number">
                <span class="question-all-answers-line"></span>
                <span class="question-all-answers-title" style="font-weight: 700;">提出回答</span>
            </div>
			<form action="QuestionServlet?method=upComment" method="post" class="postContext">
				<input type="hidden" value="${topic.getIdtopic() }" name="idtopic">
			    <textarea cols="40" rows="8" name="content" class="contextVal"></textarea>
			    <input type="submit" value="提交评价" class="upContext" id="submitComment">
			</form>
        </div>
    </div>
</div>

<%@ include file="taile.jsp"%>
</body>
</html>