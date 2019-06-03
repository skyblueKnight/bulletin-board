package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.CommentService;
import com.example.service.ArticleService;

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
	public ArticleForm setUpForm() {
		return new ArticleForm();
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
		
		/** コメント一覧を取得する */
		for(Article article : articleList) {
			article.setCommentList(commentService.findByArticleId(article.getId()));		
		}
		
		model.addAttribute("articleList",articleList);
		return "bulletin-board-page";
	}
	
	
	
	/**
	 * 記事を投稿（追加）する.<br>
	 * 追加後、掲示板ページに遷移する。
	 * 
	 * @param form 入力された記事
	 * @param model モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("/post-an-article")
	public String postAnArticle(ArticleForm form, Model model) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.insert(article);
		
		return index(model);
	}
	
}
