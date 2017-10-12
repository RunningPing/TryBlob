<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

</style>

<div class="data_list">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/images/list_icon.png"/>
		文章列表</div>
		<div class="diary_datas">
			<ul>
				<c:forEach var="article" items="${articleList }">
					<li><font face="Freestyle Script" size="5px" color="#99A5C2"><fmt:formatDate value="${article.releaseDate }" type="date" pattern="yyyy-MM-dd"/></font><a href="article?action=show&id=${article.id }"><span><font face="宋体" size="4px" >&nbsp;${article.title }</font></span></a></li>
				</c:forEach>
			</ul>
		</div>

</div>