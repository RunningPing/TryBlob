# TryBlob
## 前言

搭建一个博客的方式有许多，这里总结一下我使用JSP&Servlet搭建的博客。

## 思路

吸取别人博客的一些经验，我的博客大致思路是这样的，使用数据库建立一个表存储我写的文章，然后再建立一个表存储游客对我的文章的评论。这样，我可以直接向数据库里存储我写好的html页面，别人访问我的博客时即可阅读我上传的文章，同时也可以在留言区评论我的页面。使用的主要就是JSP&Servlet和数据库的一些知识，网页UI使用了Bootstrap框架。

## 源码分析

主页面：

```html
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
```
这是jsp主页面部分，侧边栏部分使用el表达式取出一些变量的值来显示博客的文章分布，包含的mainPage里存储url页面显示用户浏览的文章内容。

---

MainServlet处理主要的逻辑：
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		ServletContext application=request.getServletContext();
		String page="1";
		Article article=new Article();
		
		Connection con=null;

		try {
			con=dbUtil.getCon();
			List<Article> articleList=articleDao.articleList(con,article);
			List<Article> articleList2;
			if(articleList.size()>5){
				articleList2=articleList.subList(1, 5);
			}else{
				articleList2=articleList;
			}
			int total=articleDao.articleCount(con,article);
			article=articleList.get(0);
			article.setContentLittle(article.getContentLittle()+"...");
			request.setAttribute("articleList", articleList2);
			request.setAttribute("article", article);
			application.setAttribute("articleTypeCountList", articleTypeDao.articleTypeCountList(con));
			application.setAttribute("articleCountList", articleDao.articleCountList(con));
			request.setAttribute("mainPage", "article/articleMain.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
```

---

文章内容显示的逻辑ArticleServlet:

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("show".equals(action)){
			articleShow(request,response);
		}else if("list".equals(action)){
			articleList(request,response);
		}else if("listType".equals(action)){
			articleTypeList(request,response);
		}else if("message".equals(action)){
			try {
				articleMessage(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("about".equals(action)){
			request.setAttribute("mainPage", "article/articleAbout.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}
	}

	private void articleMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Message message=new Message();
		String flagMessage=request.getParameter("userMessage");
		String flagName=request.getParameter("userName");
		String flagEmail=request.getParameter("userEmail");
		
		if(StringUtil.isNotEmpty(flagMessage)){
			message.setMessage(flagMessage);
			if(StringUtil.isNotEmpty(flagName)){
				message.setName(flagName);
			}
			if(StringUtil.isNotEmpty(flagEmail)){
				message.setEmail(flagEmail);
			}
			int flagAdd=messageDao.messageAdd(dbUtil.getCon(), message);
			request.setAttribute("flagAdd",flagAdd);
		}
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Message> messageList=messageDao.messageList(con);
			request.setAttribute("messageList", messageList);
			request.setAttribute("mainPage", "article/articleMessage.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private void articleTypeList(HttpServletRequest request, HttpServletResponse response) {
		Article article=new Article();
		article.setTypeId(Integer.parseInt(request.getParameter("typeId")));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Article> articleList=articleDao.articleList(con,article);
			int total=articleDao.articleCount(con,article);
			request.setAttribute("articleList", articleList);
			request.setAttribute("mainPage", "article/articleList.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void articleList(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, FileNotFoundException {
		String page="1";
		Article article=new Article();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Article> articleList=articleDao.articleList(con,article);
			int total=articleDao.articleCount(con,article);
			request.setAttribute("articleList", articleList);
			request.setAttribute("mainPage", "article/articleList.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	private void articleShow(HttpServletRequest request, HttpServletResponse response) {
		Article article=new Article();
		article.setId(Integer.parseInt(request.getParameter("id")));
		Connection con=null;
		HttpSession session=request.getSession();
		try{
			con=dbUtil.getCon();
			List<Article> articleList=articleDao.articleList(con,article);
			article=articleList.get(0);
			session.setAttribute("article", article);
			request.setAttribute("mainPage", "article/articleShow.jsp");
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
```
---
完整的源代码地址：github：https://github.com/RunningPing/TryBlob



