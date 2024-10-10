package edu.du.copy17.service;

import edu.du.copy17.model.Article;

public class WritingRequest {
	
	private String writerName;
	private String password;
	private String title;
	private String content;
	
	public WritingRequest(String name,String title,String password,String content) {
		this.writerName=name;
		this.title=title;
		this.password=password;
		this.content=content;
	}
	
	public WritingRequest() {
		super();
	}

	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public Article toArticle() {
		Article article = new Article();
		article.setWriterName(writerName);
		article.setPassword(password);
		article.setTitle(title);
		article.setContent(content);
		return article;
	}
}
