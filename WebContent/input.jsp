<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    ${msg }
    
<% 
	if(session.isNew() || session.getAttribute("id")==null){    

%>
<form action="logInOut.jsp" method="post">
ID: <input type="text" name="id"><br>
비밀번호:<input type="password" name="pwd"><br>
<input type="submit" value="로그인">
</form>
<%}else{ %>
<p>
<a href="logInOut.jsp">로그아웃</a>
<%}%>