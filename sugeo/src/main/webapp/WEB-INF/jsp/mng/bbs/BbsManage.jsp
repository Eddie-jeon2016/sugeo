
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
$(document).ready(function(){
	
	location.href='/mng/bbs/BbsManageList.do';
});
	
</script>