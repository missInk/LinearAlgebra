<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线代学习网站主界面</title>


<!-- jQuery -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>

<!-- SET: STYLESHEET -->
<link href="${pageContext.request.contextPath }/css/style_index.css" rel="stylesheet" type="text/css" media="all" />
<!-- END: STYLESHEET -->

</head>
<body>
<%@ include file="title.jsp"%>

<!-- maincontent Starts -->
  <div class="container">
    <div class="container-in">
      <div class="read1">
        <div class="read1-img1"> <img src="${pageContext.request.contextPath }/images/read1-img.png" width="32" height="32" alt="img" /> </div>
        <h3>自动解答</h3>
        <p>自动解答模块可以完成一些基本的线性代数计算，比如:行列式求值、矩阵加法和乘法、矩阵的转置、实现逆矩阵、求矩阵的秩、 线性相关的判断、矩阵的特征值、判断两个矩阵是否相似</p>
        <a href="#">进入该模块</a> </div>
      <div class="read1">
        <div class="read1-img1"> <img src="${pageContext.request.contextPath }/images/read2-img.png" width="32" height="32" alt="img" /> </div>
        <h3>讨论问答</h3>
        <p>在讨论问答模块中，你可以将一些比较困难的题目以问题的形式发布出来给该网站的所有用户观看，你们可以就这些问题提出自己的看法<br/><br/></p>
        <a href="${pageContext.request.contextPath }/index_questions.jsp">进入该模块</a> </div>
      <div class="read1 last">
        <div class="read1-img1"> <img src="${pageContext.request.contextPath }/images/read3-img.png" width="32" height="32" alt="img" /> </div>
        <h3>我的记录</h3>
        <p>在该模块中，你可以查看到你所有的做题历史<br/><br/><br/><br/></p>
        <a href="#">进入该模块</a> </div>
      <div class="clear"></div>
      <div class="welcome">
        <h5>蓝点工作室17级寒假任务</h5>
        <p>各位17级蓝点工作室的同学们，这个寒假对于你们非常关键，此次任务将会成为你们成为正式成员的一个重要考核，希望你们给予高度重视，认真对待。脚踏实地把Javaweb基础学好，不要急于求成。<br/><br/>完成项目有时间多去了解git，码云，maven，之后把做好的项目放到码云上，试着建Maven项目，再此之前把本地项目备份好，以免出错。做项目时多看看阿里巴巴开发手册。</p>
      </div>
      <div class="clear"></div>
    </div>
  </div>
  <!-- aincontent ends --> 

<%@ include file="taile.jsp"%>

</body>
</html>