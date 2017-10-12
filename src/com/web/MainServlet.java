package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ArticleDao;
import com.dao.ArticleTypeDao;
import com.model.Article;
import com.model.PageBean;
import com.util.DbUtil;
import com.util.PropertiesUtil;
import com.util.StringUtil;

public class MainServlet extends HttpServlet{
	
	DbUtil dbUtil=new DbUtil();
	ArticleDao articleDao=new ArticleDao();
	ArticleTypeDao articleTypeDao=new ArticleTypeDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		ServletContext application=request.getServletContext();
		String page="1";
		Article article=new Article();
		
		Connection con=null;
//		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

	
}
