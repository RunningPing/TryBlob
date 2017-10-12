package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Article;
import com.model.PageBean;
import com.util.DateUtil;
import com.util.StringUtil;


public class ArticleDao {

	public List<Article> articleList(Connection con,Article s_article)throws Exception{
		List<Article> articleList=new ArrayList<Article>();
		StringBuffer sb=new StringBuffer("select * from article t1,articleType t2 where t1.typeId=t2.typeId ");
		if(StringUtil.isNotEmpty(s_article.getTitle())){
			sb.append(" and t1.title like '%"+s_article.getTitle()+"%'");
		}
		if(s_article.getId()!=-1){
			sb.append(" and t1.id="+s_article.getId());
		}
		if(s_article.getTypeId()!=-1){
			sb.append(" and t1.typeId="+s_article.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_article.getReleaseDateStr())){
			sb.append(" and DATE_FORMAT(t1.releaseDate,'%Y年%m月')='"+s_article.getReleaseDateStr()+"'");
		}
		sb.append(" order by t1.releaseDate desc");
//		if(pageBean!=null){
//			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
//		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Article article=new Article();
			article.setId(rs.getInt("id"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setContentLittle(rs.getString("contentLittle"));
			article.setReleaseDate(DateUtil.formatString(rs.getString("releaseDate"), "yyyy-MM-dd HH:mm:ss"));
			articleList.add(article);
		}
		return articleList;
	}
	
	public int articleCount(Connection con,Article s_article)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from article t1,articleType t2 where t1.typeId=t2.typeId ");
		if(StringUtil.isNotEmpty(s_article.getTitle())){
			sb.append(" and t1.title like '%"+s_article.getTitle()+"%'");
		}
		if(s_article.getTypeId()!=-1){
			sb.append(" and t1.typeId="+s_article.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_article.getReleaseDateStr())){
			sb.append(" and DATE_FORMAT(t1.releaseDate,'%Y年%m月')='"+s_article.getReleaseDateStr()+"'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public List<Article> articleCountList(Connection con)throws Exception{
		List<Article> articleCountList=new ArrayList<Article>();
		String sql="SELECT DATE_FORMAT(releaseDate,'%Y年%m月') as releaseDateStr ,COUNT(*) AS articleCount  FROM article GROUP BY DATE_FORMAT(releaseDate,'%Y年%m月') ORDER BY DATE_FORMAT(releaseDate,'%Y年%m月') DESC;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Article article=new Article();
			article.setReleaseDateStr(rs.getString("releaseDateStr"));
			article.setArticleCount(rs.getInt("articleCount"));
			articleCountList.add(article);
		}
		return articleCountList;
	}
}
