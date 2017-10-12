package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.ArticleType;
import com.util.DbUtil;

public class ArticleTypeDao {

	public List<ArticleType> articleTypeCountList(Connection con)throws Exception{
		List<ArticleType> articleCountCountList=new ArrayList<ArticleType>();
		String sql="SELECT t2.typeId,t2.typeName,COUNT(t1.id) as articleCount FROM article as t1 RIGHT JOIN articleType as t2 ON t1.typeId=t2.typeId GROUP BY t2.typeName;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			ArticleType articleCount=new ArticleType();
			articleCount.setTypeId(rs.getInt("typeId"));
			articleCount.setTypeName(rs.getString("typeName"));
			articleCount.setArticleCount(rs.getInt("articleCount"));
			articleCountCountList.add(articleCount);
		}
		return articleCountCountList;
	}
	public static void main(String[] args) {
		ArticleTypeDao test=new ArticleTypeDao();
		try {
			List<ArticleType> articleCountCountList=test.articleTypeCountList(new DbUtil().getCon());
			for(int i=0;i<articleCountCountList.size();i++){
				System.out.println(articleCountCountList.get(i).getTypeId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
