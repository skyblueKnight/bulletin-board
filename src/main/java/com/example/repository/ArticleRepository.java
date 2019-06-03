package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Article;

/**
 * 記事テーブル（articles）を操作するリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	
}
