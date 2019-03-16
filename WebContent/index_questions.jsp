<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问答模块主页</title>

<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>

<!-- SET: STYLESHEET -->
<link href="${pageContext.request.contextPath }/css/style_index_questions.css" rel="stylesheet" type="text/css" media="all" />
<!-- END: STYLESHEET -->

<script type="text/javascript">
//全局变量
var pageSize = 9;
var table_name = "linearalgebra.topic";
var pages = 0;
var amount = 0;

$(document).ready(function(){
	var url = "${pageContext.request.contextPath }/QuestionServlet?method=getQuestionsAmount";
	var args = {"tableName":table_name};
	$.post(url,args,function(data){
		amount = data;
		pages = amount/pageSize;
		for(var i = 0; i < pages; i++){
			$("#selectPage").append("<option value="+(i+1)+">"+(i+1)+"</option>");
			goPageindex(1);
		}
	})
});

function goPage(){
	if($("#selectPage").val() != 0){
		goPageindex($("#selectPage").val());
		$("#pageIndex").text($("#selectPage").val());
	}
}

function goUp(){
	if($("#pageIndex").text() == 1){
		alert("已经是第一页了");
	}else{
		$("#pageIndex").text(Number($("#pageIndex").text())-1);
		goPageindex($("#pageIndex").text());
	}
}

function goDown(){
	if($("#pageIndex").text() < pages){
		$("#pageIndex").text(Number($("#pageIndex").text())+1);
		goPageindex($("#pageIndex").text());
	}else{
		alert("已经是最后一页了");
	}
}

function checkNewTop(){
	<% if(session.getAttribute("uid") == null){ %>
	if(window.confirm('请先登录')){
		window.location = "${pageContext.request.contextPath }/sign-in.jsp";
		return;
	}
	<% } %>
	window.location = "${pageContext.request.contextPath }/postQuestions.jsp";
	
}

function flush(){
	goPageindex($("#pageIndex").text());
}

function goPageindex(pageindex){
	var url = "${pageContext.request.contextPath }/QuestionServlet?method=goPage";
	var args = {"pageSize":pageSize,"selectPage":pageindex};
	$.post(url,args,function(data){
		$("#valueContent").html(data);
	})
}
</script>

</head>
<body>
<%@ include file="title.jsp"%>

	<div class="tbody" style="margin-left:174.500px">
    <div>
        <div class="editMenu">
            <font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
            <input type="button" value="发帖" class="butt" style="width: 80px;" onclick="checkNewTop()"></font></font> <font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
            <input type="button" value="刷新" onclick="flush()" class="butt" style="width: 80px;"></font></font>
        </div>
        <div class="pageNav" align="right">
            <button style="width: 80px;" onclick="goUp()"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">上一页</font></font></button>
            <button disabled="disabled" id="pageIndex"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">1</font></font></button>
            <button style="width: 80px;" onclick="goDown()"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">下一页</font></font></button>
        </div>
        <div class="pageGo" align="right">
            <form action="topic_GetHotTopic.action" method="post" name="goPageForm">
                <input type="text" id="currentPage" value="1" style="display: none">
                <font style="vertical-align: inherit;"> 第 </font>
                <select onchange="goPage();" id="selectPage" style="width:70px;height:24px;border-radius:0;border: 1px solid silver;">
                	<option value="0">请选择</option>
            	</select>
            	<font style="vertical-align: inherit;">页 </font>
            </form>
        </div>
    </div>
    <div class="titleStyle" align="left">
        <table class="tableStyle">
            <tbody>
            <tr>
                <td class="td1">标题</td>
                <td class="td2">作者</td>
                <td class="td3">状态</td>
                <td class="td4">回复</td>
                <td class="td5">发布时间</td>
            </tr>
            </tbody></table>
    </div>
    <div class="listTopicDiv">
        <table class="listTopicStyle">
            <tbody class="tableStyle" id="valueContent">
            </tbody>
        </table>
    </div>
</div>

</body>
</html>