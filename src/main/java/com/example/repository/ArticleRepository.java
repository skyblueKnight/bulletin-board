package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

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
	 * 記事ドメインのリストを返すResultSetExtractor. コメントリストも取得して返す。 記事テーブルとコメントテーブルの結合後に使用する。
	 */
	private final static ResultSetExtractor<List<Article>> ARTICLE_RS_EXTRACTOR = (rs) -> {

		List<Article> articleList = new ArrayList<>();
		/** 初めの一行に移動 && 次の行が存在するかのチェック変数を作成 */
		boolean isCheck = rs.next();

		while (isCheck) {
			/** 記事情報の取得 */
			Article article;
			article = new Article();
			article.setId(rs.getInt("id"));
			article.setName(rs.getString("name"));
			article.setContent(rs.getString("content"));

			/** 記事にコメントがなければ、コメント一覧はnullを入れて次の行へ。*
			 * コメントがあれば、違う記事のコメントになるまでコメントを取得する。
			 * （違う記事の行になった場合は、それまでのコメント一覧をarticleに入れて
			 * そのままループの先頭（記事情報の取得）へ）*/
			List<Comment> commentList;
			if (rs.getInt("com_id") == 0) {
				commentList = null;
				isCheck = rs.next();
			} else {
				commentList = new ArrayList<>();
				while (rs.getInt("article_id") == article.getId()) {
					Comment comment = new Comment();
					comment.setId(rs.getInt("com_id"));
					comment.setName(rs.getString("com_name"));
					comment.setContent(rs.getString("com_content"));
					comment.setArticleId(rs.getInt("article_id"));
					commentList.add(comment);
					isCheck = rs.next();
					/** （次の行がなければ終了） */
					if(isCheck==false) break;
				}
			}
			article.setCommentList(commentList);
			articleList.add(article);
		}

		return articleList;

	};

	/**
	 * 全件検索を行う. 各記事のコメントも取得する。
	 * 
	 * @return 取得した記事一覧
	 */
	public List<Article> findAll() {

		// String sql = "SELECT id, name, content FROM articles ORDER BY id DESC;";
		String sql = "SELECT a.id, a.name, a.content, c.id AS com_id,"
				+ " c.name AS com_name, c.content AS com_content, c.article_id"
				+ " FROM articles a LEFT OUTER JOIN comments c ON (a.id = c.article_id)"
				+ " ORDER BY a.id DESC,c.id DESC;";

		List<Article> articleList = template.query(sql, ARTICLE_RS_EXTRACTOR);

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
