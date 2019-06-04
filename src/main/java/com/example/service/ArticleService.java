package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

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
	private ArticleRepository repository;
	
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		return repository.findAll();
	}
	
	
	/**
	 * 記事を追加する.
	 * 
	 * @param article 追加する記事
	 */
	public void insert(Article article) {
		repository.insert(article);
	}
	
	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索するID
	 * @return 取得した記事
	 */
	public Article load(int id) {		
		return repository.load(id);
	}
	
	
}
