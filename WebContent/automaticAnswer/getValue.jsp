<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
		$(function(){
			$("#getValue").click(function(){
				var xhr = new XMLHttpRequest();
				var form = document.getElementById('myform');
				var formData = new FormData(form);
				xhr.open('POST', '${pageContext.request.contextPath }/AutoAnswerServlet?method=getValue');
				xhr.send(formData);
				xhr.onreadystatechange=function(){
		            if(xhr.readyState==4 && xhr.status==200){
		            	var data = xhr.responseText;
		            	$("#value").html(data);
		            }
		        };
			})
		})
</script>

<form method="post" enctype="multipart/form-data" id="myform">
	文件：<input type="file" name="linearAlgebra" /> <input type="button"
		id="getValue" value="开始计算" />
</form>

<div id="value">
</div>