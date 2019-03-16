<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	//全局变量
	var pageSize = 9;
	var pages = 0;
	var amount = 0;
	var idtopic = <%= request.getParameter("idTopic") %>;

	$(document).ready(
	function() {
		var url = "${pageContext.request.contextPath }/BackMangerServlet?method=goCommentCont";
		var args = {
			"idtopic" : idtopic
		};
		$.post(url, args, function(data) {
			amount = data;
			pages = amount / pageSize;
			for (var i = 0; i < pages; i++) {
				$("#selectPage").append("<option value=" + (i + 1) + ">"+ (i + 1) + "</option>");
				goPageindex(1);
			}
		})
	});

	function goPage() {
		if ($("#selectPage").val() != 0 && $("#selectPage").val() != $("#pageIndex").text()) {
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
		var url = "${pageContext.request.contextPath }/BackMangerServlet?method=goComment";
		var args = {
			"pageSize" : pageSize,
			"selectPage" : pageindex,
			"idtopic": idtopic
		};
		$.post(url, args, function(data) {
			$("#valueContent").html(data);
		})
	}
</script>

<div class="tpl-portlet-components" style="display: block;">
	<div class="portlet-title">
		<div class="caption font-green bold">
			<span class="am-icon-code">评论管理 列表</span>
		</div>
	</div>
	<div class="tpl-block">
		<div class="am-g">
			<!-- 搜索框开始 -->
			<div class="am-u-sm-12 am-u-md-3">
				<div class="am-input-group am-input-group-sm">
					<input type="text" class="am-form-field" id="findProductionInput"
						placeholder="请输入被查找评论的关键字" required=""> <span
						class="am-input-group-btn">
						<button onclick="findByIdproduction()"
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
							<td class="td1">评论内容</td>
							<td class="td2">作者</td>
							<td class="td3">楼层</td>
							<td class="td5">发布时间</td>
							<td>可进行的操作</td>
						</tr>
					</tbody>
				</table>
				<div class="listTopicDiv">
					<table class="titleStyle">
						<tbody id="valueContent">
						<!-- 存放评论表格 -->
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
							style="display: none"> 第  <select
							onchange="goPage();" id="selectPage"
							style="width: 70px; height: 24px; border-radius: 0; border: 1px solid silver;">
							<option value="0">请选择</option>
						</select> 页 
					</form>
				</div>
			</div>
		</div>
		<!-- 管理界面结束 -->
	</div>
</div>



