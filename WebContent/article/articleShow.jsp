<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/style/diary.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
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

	p{
		line-height:30px;
	}
</style>

<div style="padding: 20px; padding-top:1px"class="data_list">

<div class="panel panel-default" style="padding: 15px" >
  <div  style="padding: 5px;padding-left: 10px">
    <h3><h3><a href="article?action=show&id=${article.id }"  style="color:black;text-decoration:none;"><font face="宋体" size="6px" >${article.title }</font></a></h3></h3>
  			<div style="padding-top: 1px">
				<img src="${pageContext.request.contextPath}/images/touxiang3.jpg" height="20px" width="20px" class="img-circle">
				<font face="宋体" size="4px" color="#99A5C2">一叶知秋 发表于</font>
				<font face="Freestyle Script" size="5px" color="#99A5C2"><fmt:formatDate value="${article.releaseDate }" type="date" pattern="yyyy-MM-dd"/></font>
				<a href="article?action=show&id=${article.id }"><font face="宋体" size="4px" >${article.title }</font></a>
			</div> 
  </div>
  <hr style="border:1px dashed #DDDDDD; height:1px">
  <div class="panel-body" style="padding-bottom: 20px">
 <p style="line-height:40px"><font size="4px" face="宋体" color="#000000">${article.content}</font></p>
  </div>
</div>
</div>