package com.model;

import java.util.Date;

public class Article {
	private int id=-1;
	private String title;
	private String content;
	private String contentLittle;
	private int typeId=-1;
	private String typeName;
	private Date releaseDate;
	private String releaseDateStr;
	private int articleCount;
	
	public Article() {
		super();
	}
	
	public String getContentLittle() {
		return contentLittle;
	}

	public void setContentLittle(String contentLittle) {
		this.contentLittle = contentLittle;
	}
	
	public Article(String title, String content, int typeId) {
		super();
		this.title = title;
		this.content = content;
		this.typeId = typeId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int aticleCount) {
		this.articleCount = aticleCount;
	}
}
