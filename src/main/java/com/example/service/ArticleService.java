package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事テーブル(articles)の操作を行うサービス.
 * 
 * @author momoyo kanie
 *
 */
@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 全件検索を行う.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	/**
	 * 記事を追加する.
	 * 
	 * @param article 追加する記事
	 */
	public void insert(Article article) {
		articleRepository.insert(article);
	}

	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索するID
	 * @return 取得した記事
	 */
	public Article load(int id) {
		return articleRepository.load(id);
	}

	/**
	 * 指定されたIDの記事を消去する.
	 * 
	 * @param id 消去する記事のID
	 */
	public void deleteArticleAndComment(int id) {
		commentRepository.deleteByArticleId(id);
		articleRepository.deleteById(id);
	}

}
