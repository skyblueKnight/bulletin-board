package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * コメント入力のフォーム.
 * 
 * @author momoyo kanie
 */
public class CommentForm {

	/** コメント者名 */
	@NotBlank(message="名前を⼊⼒して下さい")
	@Size(max=50, message="名前は50字以内で⼊⼒してください")
	private String name;
	/** コメント内容 */
	@NotBlank(message="投稿内容を入力してください")
	private String content;
	/** 記事ID */
	private Integer articleId;

	// ----- setter getter ----- //
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

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}


}
