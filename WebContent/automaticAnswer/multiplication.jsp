<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	var url = '${pageContext.request.contextPath }/AutoAnswerServlet?method=matrixMultiplication';
	$("#matrixMultiplication").click(function(){
		url = '${pageContext.request.contextPath }/AutoAnswerServlet?method=matrixMultiplication';
		startValculate();
	})
	
	$("#add").click(function(){
		url = '${pageContext.request.contextPath }/AutoAnswerServlet?method=add';
		startValculate();
	})
	
	function startValculate(){
		var xhr = new XMLHttpRequest();
		var form = document.getElementById('myform');
		var formData = new FormData(form);
		xhr.open('POST', url);
		xhr.send(formData);
		xhr.onreadystatechange=function(){
            if(xhr.readyState==4 && xhr.status==200){
            	var data = xhr.responseText;
            	$("#value").html(data);
            }
        };
	}
})
</script>

<form method="post" enctype="multipart/form-data" id="myform">
	文件：<input type="file" name="linearAlgebraA" /> 
	<input type="file" name="linearAlgebraB" />
	<input type="button" id="matrixMultiplication" value="开始乘法计算" />
	<input type="button" id="add" value="开始加法计算" />
</form>

<div id="value">
</div>