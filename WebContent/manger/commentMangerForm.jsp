<%@page import="lxx.linearAlgebra.entity.Comment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
List<Comment> list = (List<Comment>)request.getAttribute("list");
for(int i = 0; i < pageSize && i < list.size(); i++){
%>
<tr>
	<td class='td1'>
		<%= list.get(i).getContent() %>
	</td>
	<td class='td2'>
		<%= list.get(i).getUname() %>
	</td>
	<td class='td3'>
		<%= list.get(i).getFloor()%>
	</td>
	<td class='td5'>
		<%= list.get(i).getComment_time() %>
	</td>
	<td>
		<button onclick="">删除评论</button>
	</td>
</tr>
<%}%>