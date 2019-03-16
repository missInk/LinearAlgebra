<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	//全局变量
	var pageSize = 9;
	var table_name = "linearalgebra.topic";
	var pages = 0;
	var amount = 0;

	$(document)
			.ready(
					function() {
						var url = "${pageContext.request.contextPath }/QuestionServlet?method=getQuestionsAmount";
						var args = {
							"tableName" : table_name
						};
						$.post(url, args, function(data) {
							amount = data;
							pages = amount / pageSize;
							for (var i = 0; i < pages; i++) {
								$("#selectPage").append(
										"<option value=" + (i + 1) + ">"
												+ (i + 1) + "</option>");
								goPageindex(1);
							}
						})
					});

	function goPage() {
		if ($("#selectPage").val() != 0
				&& $("#selectPage").val() != $("#pageIndex").text()) {
			goPageindex($("#selectPage").val());
			$("#pageIndex").text($("#selectPage").val());
		}
	}

	function goUp() {
		if ($("#pageIndex").text() == 1) {
			alert("已经是第一页了");
		} else {
			$("#pageIndex").text(Number($("#pageIndex").text()) - 1);
			goPageindex($("#pageIndex").text());
		}
	}

	function goDown() {
		if ($("#pageIndex").text() < pages) {
			$("#pageIndex").text(Number($("#pageIndex").text()) + 1);
			goPageindex($("#pageIndex").text());
		} else {
			alert("已经是最后一页了");
		}
	}

	function goPageindex(pageindex) {
		var url = "${pageContext.request.contextPath }/BackMangerServlet?method=goTopicPage";
		var args = {
			"pageSize" : pageSize,
			"selectPage" : pageindex
		};
		$.post(url, args, function(data) {
			$("#valueContent").html(data);
		})
	}
</script>
    
<script type="text/javascript">
function goComment(idTopic){
	var data = {"idTopic":idTopic};
	$("#leftContainer").load("${pageContext.request.contextPath }/manger/commentManger.jsp",data);
}
function findTopic(){
	var val = document.getElementById("findProductionInput").value;
	var xml = new XMLHttpRequest();
	var url = "${pageContext.request.contextPath }/BackMangerServlet?method=findTopic";
	xml.open("post", url, true);
	xml.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
	xml.send("val="+val);
	xml.onreadystatechange = function(){
		if(xml.readyState == 4 && xml.status == 200){
			document.getElementById("valueContent").innerHTML = xml.responseText;
		}
	}
}
function delTopic(idtopic){
	var xml = new XMLHttpRequest();
	var url = "${pageContext.request.contextPath }/BackMangerServlet?method=delTopic";
	xml.open("post", url, true);
	xml.setRequestHeader("Content-type","application/x-www-form-urlencoded; charset=UTF-8");
	xml.send("idtopic="+idtopic);
	xml.onreadystatechange = function(){
		if(xml.readyState == 4 && xml.status == 200){
			alert(xml.responseText);
		}
	}
}
</script>

<div class="tpl-portlet-components" style="display: block;">
	<div class="portlet-title">
		<div class="caption font-green bold">
			<span class="am-icon-code">问题管理 列表</span>
		</div>
	</div>
	<div class="tpl-block">
		<div class="am-g">
			<!-- 搜索框开始 -->
			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<input type="text" class="am-form-field" id="findProductionInput"
						placeholder="请输入被查找问题的关键字" required=""> <span
						class="am-input-group-btn">
						<button onclick="findTopic()"
							class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search">开始搜索</button>
					</span>
				</div>
			</div>
		</div>
		<!-- 搜索框结束 -->

		<!-- 管理界面开始 -->
		<div class="am-g valueContainer">
			<div align="left">

				<table class="titleStyle">
					<tbody>
						<tr>
							<td class="td1">标题</td>
							<td class="td2">作者</td>
							<td class="td3">状态</td>
							<td class="td4">回复量</td>
							<td class="td5">发布时间</td>
							<td>可进行的操作</td>
						</tr>
					</tbody>
				</table>
				<div class="listTopicDiv">
					<table class="titleStyle">
						<tbody id="valueContent">
						<!-- 存放表格 -->
						</tbody>
					</table>
				</div>
				<div class="pageNav" align="right">
					<button style="width: 80px;" onclick="goUp()">上一页</button>
					<button disabled="disabled" id="pageIndex">1</button>
					<button style="width: 80px;" onclick="goDown()">下一页</button>
				</div>
				<div class="pageGo" align="right">
					<form action="topic_GetHotTopic.action" method="post"
						name="goPageForm">
						<input type="text" id="currentPage" value="1"
							style="display: none"> <font
							style="vertical-align: inherit;"> 第 </font> <select
							onchange="goPage();" id="selectPage"
							style="width: 70px; height: 24px; border-radius: 0; border: 1px solid silver;">
							<option value="0">请选择</option>
						</select> <font style="vertical-align: inherit;">页 </font>
					</form>
				</div>
			</div>
		</div>
		<!-- 管理界面结束 -->
	</div>
</div>

