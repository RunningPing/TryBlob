package com.model;

public class ArticleType {

	private int typeId;
	private String typeName;
	private int articleCount;
	

	
	public ArticleType() {
		super();
	}
	public ArticleType(String typeName) {
		super();
		this.typeName = typeName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int diaryCount) {
		this.articleCount = diaryCount;
	}
	
	
}
