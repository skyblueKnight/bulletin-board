package com.example.form;

/**
 * コメント入力のフォーム.
 * 
 * @author momoyo kanie
 */
public class CommentForm {

	/** コメント者名 */
	private String name;
	/** コメント内容 */
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
