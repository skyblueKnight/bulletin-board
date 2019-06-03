package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事テーブル（articles）を操作するリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
@Repository
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
	
	
	
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return 取得した記事一覧
	 */
	public List<Article> findAll(){
		
		String sql="SELECT id, name, content FROM articles ORDER BY id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		
		return articleList;
	}
	
	
	
	
}
