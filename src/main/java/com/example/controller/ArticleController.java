package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.service.ArticleService;

@Controller
@RequestMapping("/bulletin-board")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	
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
	 * 記事一覧を取得する。
	 * 
	 * @param model モデル
	 * @return 掲示板ページ
	 */
	@RequestMapping("")
	public String index(Model model) {
		
		List<Article> articleList = articleService.findAll();
		model.addAttribute("articleList",articleList);
		
		return "bulletin-board-page";
	}
	
	
	
	@RequestMapping("/post-an-article")
	public String postAnArticle(ArticleForm form, Model model) {
		
		
		return index(model);
	}
	
}
