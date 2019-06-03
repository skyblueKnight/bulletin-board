package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事テーブル(articles)の操作を行うサービス.
 * 
 * @author momoyo kanie
 *
 */
@Service
public class ArticleService {

	@Autowired 
	private ArticleRepository repository;
	
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return 記事一覧
	 */
	public List<Article> findAll(){
		System.out.println("service");
		return repository.findAll();
	}
	
	
	
}
