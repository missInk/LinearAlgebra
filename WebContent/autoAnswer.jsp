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
<link href="${pageContext.request.contextPath }/css/AutomaticAnswer-css.css" rel="stylesheet" type="text/css" media="all" />
<!-- END: STYLESHEET -->

<script type="text/javascript">
function addLine(index){
	var file = document.getElementById("file"+index);
	file.click();
	$(file).onchange(addToTextarea(index));
}

function addToTextarea(index){
	var file = document.getElementById("file"+index).files;
	if(file[0].name != ""){
		var xhr = new XMLHttpRequest();
		var questionID = "question"+index;
		var formID = "form"+index;
		var form = document.getElementById(formID);
		var formData = new FormData(form);
		var text = "行列式";
		if(index != 'C'){
			text+=index;
		}
		text+=":\n";
		xhr.open('POST', '${pageContext.request.contextPath }/AutoAnswerServlet?method=addLine');
		xhr.send(formData);
		xhr.onreadystatechange=function(){
	        if(xhr.readyState==4 && xhr.status==200){
	        	var data = xhr.responseText;
	        	$("#"+questionID).text(text+data);
	        }
	    };
    }
}

</script>

<script type="text/javascript">
function getValue(){
	var val = $("#questionC").text();
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=getValue";
	var args = {"line":val};
	$.post(url,args,function(data){
		$("#answerC").text("结果：\n"+"行列式的值为："+data);
	})
}
function transpose(){
	var val = $("#questionC").text();
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=transpose";
	var args = {"line":val};
	$.post(url,args,function(data){
		$("#answerC").text("结果：\n"+data);
	})
}
function mrinv(){
	var val = $("#questionC").text();
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=mrinv";
	var args = {"line":val};
	$.post(url,args,function(data){
		$("#answerC").text("结果：\n"+data);
	})
}
function rank(){
	var val = $("#questionC").text();
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=rank";
	var args = {"line":val};
	$.post(url,args,function(data){
		$("#answerC").text("结果：\n"+"行列式的秩为："+data);
	})
}
function matrixMultiplication(){
	var valA = $("#questionA").text();
	var valB = $("#questionB").text();
	var url = "${pageContext.request.contextPath }/AutoAnswerServlet?method=matrixMultiplication";
	var args = {"lineA":valA,"lineB":valB};
	$.post(url,args,function(data){
		$("#answerAB").text("结果：\n"+data);
	})
}
</script>
</head>
<body>
<%@ include file="title.jsp"%>

<!-- Main Starts -->
<div id="body">
    <div class="container">
        <div class="explain">
            <span>请在第二行开始输入数据，在第一行输入的数据后台不会读取。导入行列式时会将之前输入在文本框内的数据清除掉，请谨慎导入。空格分隔数据，换行代表行列式的下一行。</span>
        </div>
        <div class="container_question">
            <div class="question">
                <textarea id="questionC">行列式：</textarea>
                <form  method="post" enctype="multipart/form-data" id="formC">
                	<input type="file" style="visibility:hidden;"  name="linearAlgebraC" id="fileC" onchange="addToTextarea('C')">
                </form>
            </div>
            <div class="answer">
                <textarea id="answerC" readonly>结果：</textarea>
            </div>
            <div class="functions">
                <input type="button" value="导入行列式" onclick="addLine('C')">
                <input type="button" value="行列式求值" onclick="getValue()">
                <input type="button" value="矩阵的转置" onclick="transpose()">
                <input type="button" value="实现逆矩阵" onclick="mrinv()">
                <input type="button" value="求矩阵的秩" onclick="rank()">
            </div>
        </div>
        <div class="explain">
            <span>计算前请先把行列式A和行列式B都输入值，规则和上规则类似</span>
        </div>
        <div class="container_question container_questionTwo">
            <div class="question questionTwo">
                <textarea id="questionA">行列式A：</textarea>
                <form  method="post" enctype="multipart/form-data" id="formA">
                	<input type="file" style="visibility:hidden;" name="linearAlgebraA" id="fileA" onchange="addToTextarea('A')">
                </form>
                <textarea id="questionB">行列式B：</textarea>
                <form  method="post" enctype="multipart/form-data" id="formB">
                	<input type="file" style="visibility:hidden;" name="linearAlgebraB" id="fileB" onchange="addToTextarea('B')">
                </form>
            </div>
            <div class="answer answerTwo">
                <textarea id="answerAB" readonly>结果：</textarea>
            </div>
            <div class="functions">
                <input type="button" value="导入行列式A" onclick="addLine('A')">
                <input type="button" value="导入行列式B" onclick="addLine('B')">
                <input type="button" value="解线性方程组">
                <input type="button" value="线性相关判断">
                <input type="button" value="矩阵加法" onclick="add()">
                <input type="button" value="矩阵乘法" onclick="matrixMultiplication()">
            </div>
        </div>
    </div>
</div>
<!-- Main ends -->

<%@ include file="taile.jsp"%>
</body>
</html>