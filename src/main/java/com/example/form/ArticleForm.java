package com.example.form;

import javax.validation.constraints.NotBlank;

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
	@NotBlank
	private String name;
	/** 投稿内容 */
	@NotBlank
	private String content;
	
	
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

	

	

}
