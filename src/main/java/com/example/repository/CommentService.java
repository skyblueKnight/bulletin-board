package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Comment;

/**
 * コメントテーブル(comments)を操作するサービス.
 * 
 * @author momoyo kanie
 */
@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;

	/**
	 * 記事IDからコメントを検索する. コメントは新しいものが上に来るよう並び替える。
	 * 
	 * @param articleId 検索する記事ID
	 * @return コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId) {

		return repository.findByArticleId(articleId);
	}

	/**
	 * 入力されたコメントを追加する.
	 * 
	 * @param comment 追加するコメント
	 */
	public void insert(Comment comment) {
		repository.insert(comment);
	}

	/**
	 * 指定されたIDのコメントを消去する.
	 * 
	 * @param id 消去するコメントのID
	 */
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	/**
	 * 指定された記事IDのコメントを消去する.
	 * 
	 * @param id 消去するコメントの記事ID
	 */
	public void deleteByArticleId(int articleId) {
		repository.deleteByArticleId(articleId);
	}

}
