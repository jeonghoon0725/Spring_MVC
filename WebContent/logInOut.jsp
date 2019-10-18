<%@page import="com.edu.test.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	RequestDispatcher rd = request.getRequestDispatcher("input.jsp");
	String method = request.getMethod();
	if(method.equals("POST")){
		//로그인
		//1. Parameter 추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//2. 유효성 체크
		if(id.isEmpty() || pwd.isEmpty()){
			request.setAttribute("msg", "ID 또는 비밀번호를 입력해주세요");
			rd.forward(request, response);
			return;
		}
		//3. DB 데이터와 비교
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.check(id, pwd);
		if(result == false){ //if(!result)
			request.setAttribute("msg", "ID 또는 비밀번호를 입력해주세요");
			rd.forward(request, response);
			return;
		}
		//4. 현재 로그인 여부 판단
		if(session.isNew() || session.getAttribute("id")==null){
			session.setAttribute("id", id);
			System.out.println("로그인 완료");
		}else{
			System.out.println("현재 로그인 상태");
		}
			
	}else if(method.equals("GET")){
		if(session != null && session.getAttribute("id")!=null){
			session.invalidate();//로그아웃 처리
			System.out.println("로그아웃 완료");
		}else{
			System.out.println("현재 로그인 상태가 아님");
		}
	}
	rd.forward(request, response);
%>
OK