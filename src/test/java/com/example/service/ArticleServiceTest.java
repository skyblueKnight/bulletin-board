package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
	
	@Autowired
	private ArticleService service;

	@Test
	public void testFindAll() {
		
		System.out.println("findAll()テスト開始");
		List<Article> articleList = service.findAll();
		assertThat("取得件数が違います",articleList.size(),is(2));
		assertThat("IDの降順になっていません",articleList.get(0).getId(), is(2));
		assertThat("IDの降順になっていません",articleList.get(1).getId(), is(1));		
	
	}

}
