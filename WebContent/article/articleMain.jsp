<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<link href="${pageContext.request.contextPath}/style/diary.css" rel="stylesheet">

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
	#wenzi *{
		vertical-align: bottom;
	}
</style>

<div >
	<div style="padding: 30px; padding-top: 10px"class="data_list">
		<div><h3><a href="article?action=show&id=${article.id }"  style="color:black;text-decoration:none;"><font face="宋体" size="6px" >${article.title }</font></a></h3></div>
		<img src="${pageContext.request.contextPath}/images/fengjing.jpg"   height="100%" width="100%" class="img-responsive" alt="Responsive image"/>
		<div id="wenzi" style="padding-top: 10px">
			<font face="宋体" size="4px"  ><p style="line-height:30px">${article.contentLittle }<p></font>
			<div style="padding-top: 1px">
				<img src="${pageContext.request.contextPath}/images/touxiang3.jpg" height="20px" width="20px" class="img-circle">
				<font face="宋体" size="4px" color="#99A5C2">一叶知秋 发表于</font>
				<font face="Freestyle Script" size="5px" color="#99A5C2"><fmt:formatDate value="${article.releaseDate }" type="date" pattern="yyyy-MM-dd"/></font>
				<a href="article?action=show&id=${article.id }"><font face="宋体" size="4px" >${article.title }</font></a>
			</div> 
		</div>
	</div>
	<div class="data_list_title" style=" padding-top: 20px">
		<h3><font face="宋体" size="5px">近期文章</font></h3>
		<hr style="border:1px dashed #DDDDDD; height:1px">
	</div>
	<div class="diary_datas" style=" padding-top: 10px">
		<ul style="list-style-type:none">
			<c:forEach var="article" items="${articleList }">
				<div style="padding-top: 5px;padding-bottom: 5px">
					<li>
						<font face="Freestyle Script" size="5px" color="#99A5C2"><fmt:formatDate value="${article.releaseDate }" type="date" pattern="yyyy-MM-dd"/></font><a href="article?action=show&id=${article.id }"><span><font face="宋体" size="4px" >&nbsp;${article.title }</font></span></a>
					</li>
				</div>
			</c:forEach>
		</ul>
	</div>
</div>
