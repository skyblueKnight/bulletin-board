package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントテーブル(comments)を操作するリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * コメントドメインを返すRow_Maper.
	 */
	private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};

	/**
	 * 記事IDからコメントを検索する. コメントは新しいものが上に来るよう並び替える。
	 * 
	 * @param articleId 検索する記事ID
	 * @return コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId) {

		String sql = "SELECT id, name, content, article_id" + " FROM comments" + " WHERE article_id=:articleId"
				+ " ORDER BY id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);

		return commentList;
	}

	/**
	 * 入力されたコメントを追加する.
	 * 
	 * @param comment 追加するコメント
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String sql = "INSERT INTO comments(name,content,article_id) VALUES(:name, :content,:articleId);";
		template.update(sql, param);
	}

	/**
	 * 指定されたIDのコメントを消去する.
	 * 
	 * @param id 消去するコメントのID
	 */
	public void deleteById(int id) {

		String sql = "DELETE FROM comments WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	
	/**
	 * 指定された記事IDのコメントを消去する.
	 * 
	 * @param id 消去するコメントの記事ID
	 */
	public void deleteByArticleId(int articleId) {

		String sql = "DELETE FROM comments WHERE article_ id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}

}
