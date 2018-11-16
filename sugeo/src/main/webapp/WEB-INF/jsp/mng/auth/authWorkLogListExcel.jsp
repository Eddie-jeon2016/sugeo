<%@ page contentType="application/vnd.ms-excel; charset=utf-8"%>
<meta http-equiv='Content-Type' content='application/vnd.ms-excel; charset=utf-8'/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
    Calendar day = Calendar.getInstance();
    String str = "" + day.get(Calendar.YEAR); // 년;
    int thisMonth = day.get(Calendar.MONTH) + 1; // 월 ( 0 ~ 11 )= 0;
    if ( thisMonth < 10 ) {
        str += "0";
    }
    str += "" + thisMonth ;
    int thisDate = day.get(Calendar.DATE) ; // 월 ( 0 ~ 11 )= 0;
    if ( thisDate < 10 ) {
        str += "0";
    }

    String listName = (String)request.getAttribute("listName");

    str += "" + thisDate ;
    String today = str;
    String f_nm="_작업로그_관리";
    response.setHeader("Content-Disposition", "inlinet; filename="+today+new String((f_nm).getBytes("KSC5601"),"8859_1")+".xls");
    response.setHeader("Content-Description", "JSP Generated Data");
%>

<h2>
    	작업로그
</h2>
<div id="maininner"><!-- maininner (s)-->
    <div class="tbl_wrp">
	<table class="tbl_lst" border=1>
	<caption></caption>
		<colgroup>
			<col style="width:8%"/>
			<col style="width:20%"/>
			<col style="width:20%"/>
			<col style="width:35%"/>
			<col style="width:35%"/>
			<col style="width:20%"/>

		</colgroup>
		<thead>
			<tr>
				<th>No</th>
				<th>작업 ID</th>
				<th>접속 IP</th>
				<th>작업 URL</th>
				<th>작업내용</th>
				<th>작업일시</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${empty resultList }">
				<tr>
					<td colspan="6">작업로그 이력이 없습니다.</td>
				<tr>
			</c:if>
			<c:if test="${not empty resultList }">
				<c:forEach var="result" items="${resultList}" varStatus="varStatus">
					<tr>
						<td >${varStatus.index+1}</td>
						<td>${result.userId }</td>
						<td>${result.accessIp }</td>
						<td>${result.url }</td>
						<td>${result.content}</td>
						<td>${result.logDate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
</div><!-- maininner (e)-->