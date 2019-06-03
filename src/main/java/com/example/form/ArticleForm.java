package com.example.form;

import java.util.List;

import com.example.domain.Comment;

/**
 * 記事テーブル(articles)のフォーム.
 * 
 * @author momoyo kanie
 *
 */
public class ArticleForm {
	
	/** ID */
	private String id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** コメントリスト */
	private List<Comment> commentList;
	
	
	// ----- setter getter ----- //
	public Integer getIntId() {
		return Integer.parseInt(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	

	

}
