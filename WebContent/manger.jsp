<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>

<!-- SET: STYLESHEET -->
<link href="${pageContext.request.contextPath }/css/manger.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath }/css/questionManger.css" rel="stylesheet" type="text/css" media="all" />
<!-- END: STYLESHEET -->

<script type="text/javascript">
function goManger(mangerName){
	var url = "${pageContext.request.contextPath }/manger/"+mangerName+".jsp";
	$("#leftContainer").load(url);
}
</script>
</head>
<body>
<%@ include file="title.jsp"%>
	
	<div class="tpl-page-container tpl-page-header-fixed">
    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-title">后台管理列表</div>
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu">
                <li class="tpl-left-nav-item menue-act">
                    <a onclick="goManger('questionManger')" class="nav-link tpl-left-nav-link-list" href="javascript:void(0);">
                        <span style="color: rgb(91, 155, 209);">问题管理</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a onclick="goManger('userManger')" class="nav-link tpl-left-nav-link-list" href="javascript:void(0);">
                        <span style="color: rgb(91, 155, 209);">人员管理</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tpl-content-wrapper" id="leftContainer">
        <%@ include file="manger/questionManger.jsp"%>
    </div>
</div>
<!-- Main ends -->
	
<%@ include file="taile.jsp"%>
</body>
</html>