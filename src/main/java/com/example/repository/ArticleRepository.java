package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

	/**
	 * 記事ドメインを返すRowMapper.
	 */
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
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
	public List<Article> findAll() {

		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);

		return articleList;
	}

	/**
	 * 記事を追加する.
	 * 
	 * @param article 追加する記事
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO articles(name,content) VALUES(:name, :content);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",
				article.getContent());
		template.update(sql, param);
	}

	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索するID
	 * @return 取得した記事
	 */
	public Article load(int id) {
		String sql = "SELECT id, name, content FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Article article = template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);

		return article;
	}

	/**
	 * 指定されたIDの記事を消去する.
	 * 
	 * @param id 消去する記事のID
	 */
	public void deleteById(int id) {

		String sql = "DELETE FROM articles WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	
	
	
	
	
	

}
