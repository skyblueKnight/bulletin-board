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
	 * 記事IDからコメントを検索する.
	 * コメントは新しいものが上に来るよう並び替える。
	 * 
	 * @param articleId 検索する記事ID
	 * @return コメント一覧
	 */
	public List<Comment> findByArticleId(int articleId) {

		return repository.findByArticleId(articleId);
	}

}
