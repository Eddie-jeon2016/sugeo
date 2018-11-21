<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="path">
    <ul class="path_inner">
        <li class="home"><a title="HOME" href="/mng/main.do"><img src="/images/common/pathhome.jpg" alt="HOME"></a></li>
        <li><a href="${parentUrl }" title="${parentName }">${parentName }</a></li>
        <li class="pathS">
            <span>${originalName }</span>
        </li>
    </ul>
</div>