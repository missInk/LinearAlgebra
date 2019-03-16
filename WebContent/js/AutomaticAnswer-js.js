/*********加载矩阵开始************/
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
/*********加载矩阵结束************/
/*********功能开始************/
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
/*********功能结束************/