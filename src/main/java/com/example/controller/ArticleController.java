package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/bulletin-board")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;

	/**
	 * 記事フォームのインスタンスを返す.
	 * 
	 * @return 記事フォームのインスタンス
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	/**
	 * コメントフォームのインスタンスを返す.
	 * 
	 * @return コメントフォーム
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 掲示板ページに遷移する.<br>
	 * 記事一覧を取得したあと、コメント一覧を取得する。
	 * 
	 * @param model モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		/** 記事一覧を取得 */
		List<Article> articleList = articleService.findAll();

//		/** コメント一覧を取得する */
//		for (Article article : articleList) {
//			article.setCommentList(commentService.findByArticleId(article.getId()));
//		}

		model.addAttribute("articleList", articleList);
		return "bulletin-board-page";
	}

	/**
	 * 記事を投稿（追加）する.<br>
	 * 追加後、掲示板ページに遷移する。
	 * 
	 * @param form  入力された記事
	 * @param model モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("/post-article")
	public String postArticle(@Validated ArticleForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return index(model);			
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.insert(article);

		return index(model);
	}

	/**
	 * コメントを投稿（追加）する.<br>
	 * 追加後、掲示板ページに遷移する。
	 * 
	 * @param form  入力されたコメント
	 * @param model モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("/post-comment")
	public String postComment(CommentForm form, Model model) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		commentService.insert(comment);

		return index(model);
	}

	/**
	 * 指定されたIDの記事と、その記事のコメントを削除する.
	 * 
	 * @param articleId 指定された（削除する）記事ID
	 * @param model     モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("/delete-article-and-comment")
	public String DeleteArticleAndComment(int articleId, Model model) {
		articleService.deleteArticleAndComment(articleId);

		return index(model);
	}

}
