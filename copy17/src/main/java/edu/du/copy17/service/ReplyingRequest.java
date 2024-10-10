package edu.du.copy17.service;


public class ReplyingRequest extends WritingRequest {

	public ReplyingRequest(String name, String title, String password, String content) {
		super(name, title, password, content);
		this.setWriterName(name);
		this.setTitle(title);
		this.setPassword(password);
		this.setContent(content);
		// TODO Auto-generated constructor stub
	}
	public ReplyingRequest() {
		// TODO Auto-generated constructor stub
	}
	private int parentArticleId;

	public int getParentArticleId() {
		return parentArticleId;
	}
	public void setParentArticleId(int parentArticleId) {
		this.parentArticleId = parentArticleId;
	}
	
}
