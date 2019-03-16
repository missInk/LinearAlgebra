<%@page import="lxx.linearAlgebra.entity.Topic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
int pageSize = 9;
List<Topic> list = (List<Topic>)request.getAttribute("list");
if(list == null){
%>
	<tr>
		<td colSpan="4">
		没有找到任何相关信息
		</td>
	</tr>
<%
}else{
for(int i = 0; i < pageSize && i < list.size(); i++){
%>
<tr>
	<td class='td1'>
		<a href="javascript:void(0);" onclick="goComment('<%= list.get(i).getIdtopic() %>')">
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
	<td>
		<button onclick="delTopic('<%= list.get(i).getIdtopic() %>')">删除问答</button>
	</td>
</tr>
<%}
}%>