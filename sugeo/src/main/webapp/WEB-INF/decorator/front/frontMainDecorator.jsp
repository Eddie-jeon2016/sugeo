<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<jsp:include page="/WEB-INF/jsp/front/include/head.jsp" />
</head>
<body>
	<div id="wrap">
		<!-- 웹 접근성 -->
      <ul id="skipToContent">
        <li><a title="탑 메뉴 바로가기" href=".hdtop">탑 메뉴 바로가기</a></li>
        <li><a title="주 메뉴 바로가기" href=".hdbtm">주 메뉴 바로가기</a></li>
        <li><a title="본문 바로가기" href=".wrap">본문 바로가기</a></li>
        <li><a title="하단 바로가기" href=".footer">하단 바로가기</a></li>
      </ul>

   
<!--     /WEB-INF/jsp/front/include/top.jsp -->
			<jsp:include page="/front/menu/top.do" />
<%-- 			<jsp:include page="/WEB-INF/jsp/front/include/secondTop.jsp" /> --%>
			<sitemesh:write property="body" />
			
			<jsp:include page="/WEB-INF/jsp/front/include/footer.jsp" />
   	</div>	
</body>
