<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布问题</title>

<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>

<!-- SET: STYLESHEET -->
<link href="${pageContext.request.contextPath }/css/style_postQuestions.css" rel="stylesheet" type="text/css"/>
<!-- END: STYLESHEET -->

<script type="text/javascript">
	function postQuestion(){
		var title = $('#tTitle').val();
		var content = $("#textarea").val();
		var url = "${pageContext.request.contextPath }/QuestionServlet?method=postQuestions";
		var args = {"title":title, "content":content};
		$.post(url,args,function(data){
			var jsonObject = eval("(" + data + ")");
			if(jsonObject.message == "false"){
				alert("上传问题失败，请重试。");
			}else if(jsonObject.message == "success"){
				window.location = "${pageContext.request.contextPath }/index_questions.jsp";
			}
		})
	}
</script>

</head>
<body>
<%@ include file="title.jsp"%>
<div style="height: 700px;">
<form id="newT" method="post" onsubmit="return validToipc()" style="margin-left: 150px;">
    <div class="titleStyle" align="left">发布问题</div>
    <div class="newStyle" style="">
        <table border="0" cellspacing="0" cellpadding="0" style="">
            <tbody style=""><tr>
                <th width="150px" align="center">文章标题</th>
                <td>
                    <input type="text" name="topic.title" id="tTitle" style="width: 626px;height: 26px;line-height: 24px;font-size: 14px;padding-left: 6px;" maxlength="50">
                    <span id="span" style="color:red;font-size:14px"> 文章标题最长为50个字符</span>
                </td>
            </tr>
            <tr style="">
                <th align="center">文章内容</th>
                <td width="700px" style=""> <textarea cols="40" rows="8" name="textarea" id="textarea" ></textarea></td>
            </tr>

            <tr>
                <th style="height: 180px;">&nbsp;</th>
                <td align="left" style="height: 180px;"><input type="button" value="发表提问" class="butt" onclick="postQuestion()"><input type="button" value="取消" class="butt" onclick="if(window.confirm('你确定要取消吗？')){history.go(-1);};">
                    <ol class="notice">
                        <li>这里发言，表示您接受了我们的用户<a href="" target="_blank">行为准则</a>。</li>
                        <li>请对您的言行负责，并遵守中华人民共和国有关法律法规,尊重网上道德。</li>
                        <li>如是商业用途请联系原作者。</li>
                    </ol></td>
            </tr>
            </tbody></table>
    </div>
</form>
</div>
<%@ include file="taile.jsp"%>
</body>
</html>