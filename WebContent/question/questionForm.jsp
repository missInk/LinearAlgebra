<%@page import="lxx.linearAlgebra.entity.Topic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int pageSize = Integer.parseInt((String)request.getParameter("pageSize"));
List<Topic> list = (List<Topic>)request.getAttribute("list");
for(int i = 0; i < pageSize && i < list.size(); i++){
%>
<tr>
	<td class='td1'>
		<a href='QuestionServlet?method=goTopic&idtopic=<%= list.get(i).getIdtopic() %>'>
			<%= list.get(i).getTitle() %>
		</a>
	</td>
	<td class='td2'>
		<%= list.get(i).getUname() %>
	</td>
	<td class='td3'>
		<%= list.get(i).getStatus()%>
	</td>
	<td class='td4'>
		<%= list.get(i).getComment_count() %>次
	</td>
	<td class='td5'>
		<%= list.get(i).getTopic_time() %>
	</td>
</tr>
<%}%>