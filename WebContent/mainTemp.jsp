<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>一叶知秋的主页</title>
<link href="${pageContext.request.contextPath}/style/diary.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<style type="text/css">
	body {
		padding-top: 60px;
		padding-bottom: 40px;
	}

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
	
	a{text-decoration:none;}
	
	body{
	a:text-decoration:none;
	}
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top navbar-right ">
      <div class="navbar-inner" >
        <div class="container" >
          <a class="brand" href="#">&nbsp;&nbsp;一叶知秋&nbsp;&nbsp;</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="main"><i class="icon-home"></i>&nbsp;主 页&nbsp;</a></li>
              <li class="active"><a href="article?action=list"><i class="icon-book"></i>&nbsp;归 档&nbsp;</a></li>
              <li class="active"><a href="article?action=message"><i class="icon-pencil"></i>&nbsp;留 言&nbsp;</a></li>
               <li class="active"><a href="article?action=about"><i class="icon-italic"></i>&nbsp;关 于&nbsp;</a></li>
            </ul>
          </div>
        </div>
      </div>
</div>
<div class="container">
	<div class="row-fluid">
		<!-- 主页显示内容 -->
		<div class="span9">
			<jsp:include page="${mainPage }"></jsp:include>
		</div>
		<!-- 侧边栏显示内容 -->
		<div class="span3">
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/images/user_icon.png"/>
					个人中心
				</div>
				<div class="user_image">
					<img src="${pageContext.request.contextPath}/images/touxiang3.jpg"  class="img-circle">
				</div>
				<div class="nickName" style="padding-top: 5px"><font face="楷体" size="5" color="grey">一叶知秋</font></div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/images/byType_icon.png"/>
					文章分类
				</div>
				<div class="datas" style="height: 190px">
					<ul style="list-style-type:none">
						<c:forEach var="articleTypeCount" items="${articleTypeCountList }">
							<li><span><a href="article?action=listType&typeId=${articleTypeCount.typeId }">${articleTypeCount.typeName }(${articleTypeCount.articleCount })</a></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>