package com.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ArticleDao;
import com.dao.ArticleTypeDao;
import com.dao.MessageDao;
import com.model.Article;
import com.model.Message;
import com.util.DbUtil;
import com.util.StringUtil;

public class ArticleServlet extends HttpServlet{

	DbUtil dbUtil=new DbUtil();
	ArticleDao articleDao=new ArticleDao();
	ArticleTypeDao articleTypeDao=new ArticleTypeDao();
	MessageDao messageDao=new MessageDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}

	@Override
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
