<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/style/diary.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
	a:link {
		text-decoration: none;
	}
	
	a:visited {
		text-decoration: none;
	}
	
	a:hover {
		text-decoration: none;
	}
	
	a:active {
		text-decoration: none;
	}
	a{
	text-decoration:none;
	}

</style>
<div class="data_list">
	<div>
		<form role="form" action="article?action=message" method="post">
			<div>
			<textarea class="ckeditor" id="userMessage" name="userMessage"></textarea>
			</div>
			<div align="right" style="padding-top: 10px">
			<div class="form-group">
			    <label for="userName">Name&nbsp;</label>
			    <input type="text" class="form-control input-lg" placeholder="Text input" id="userName" name="userName">
			 </div>
			 <div class="form-group" style="padding-top: 10px">
			    <label for="userEmail">Email&nbsp;&nbsp;</label>
			    <input type="email" class="form-control input-lg" id="userEmail" name="userEmail" placeholder="Enter email ">
			 </div>
			 <div align="right" style="padding-top: 10px">
			 	<button type="submit" class="btn btn-default">提交</button>
			 </div>
			 </div>
		</form>
	</div>
				

	<ul class="list-group" style="list-style-type:none">
	  	<c:forEach var="message" items="${messageList }">
			<li class="list-group-item">
				<h4>${message.name }</h4>
				<div>${message.message }</div> 
				<div align="right"><fmt:formatDate value="${message.releaseDate }" type="date" pattern="yyyy-MM-dd"/></div>
			</li>	
		</c:forEach>
	</ul>

</div>	
		
		

