package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@NotBlank(message="投稿者名を入力してください")
	@Size(max=50, message="名前は50字以内で⼊⼒してください")
	private String name;
	/** 投稿内容 */
	@NotBlank(message="投稿内容を入力してください")
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
