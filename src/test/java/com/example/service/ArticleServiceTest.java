package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
	
	@Autowired
	private ArticleService service;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Test
	public void testLoad() {
		
		System.out.println("load()テスト開始");
		Article article = service.load(1);
		assertThat("取得情報が間違っています",article.getId(), is(1));
		assertThat("取得情報が間違っています",article.getName(), is("伊賀"));
		assertThat("取得情報が間違っています",article.getContent(), is("この掲⽰板について"));
		System.out.println("load()テスト終了");
	
	}	
	
	@Test
	public void testFindAll() {
		
		System.out.println("findAll()テスト開始");
		List<Article> articleList = service.findAll();
		assertThat("取得件数が違います",articleList.size(),is(2));
		assertThat("IDの降順になっていません",articleList.get(0).getId(), is(2));
		assertThat("IDの降順になっていません",articleList.get(1).getId(), is(1));		
		System.out.println("findAll()テスト終了");
	
	}
	
	@Test
	public void testInsert() {
		
		System.out.println("insert()テスト開始");
		Article article = new Article();
		article.setName("蟹江");
		article.setContent("テスト投稿");
		service.insert(article);
		Article article2 = service.load(3);
		assertThat("取得情報が間違っています",article2.getId(), is(3));
		assertThat("取得情報が間違っています",article2.getName(), is("蟹江"));
		assertThat("取得情報が間違っています",article2.getContent(), is("テスト投稿"));	
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", 3);
		template.update("delete from articles where id = :id", param);
		System.out.println("入れたデータを削除しました。");
		System.out.println("insert()テスト終了");
		
	}
}
