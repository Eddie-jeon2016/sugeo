<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- <jsp:forward page="/mng/auth/selectAuthorUserList.do"> --%>
<jsp:forward page="/mng/notice/BbsDetailList.do">
	<jsp:param value="8" name="BbsId"/>
	<jsp:param value="1000008" name="menuNo"/>
</jsp:forward>