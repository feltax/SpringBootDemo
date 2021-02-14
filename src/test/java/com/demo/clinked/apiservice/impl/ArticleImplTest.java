package com.demo.clinked.apiservice.impl;

import com.demo.clinked.apiservice.ApiApplication;
import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ApiApplication.class)
class ArticleImplTest {

    @Mock
    ArticleRepository articleRepository;
    @InjectMocks
    ArticleImpl articleImpl;
    LocalDate today = LocalDate.now();
    List<Article> list = new ArrayList<>();
    Pageable firstPageWith3Articles = PageRequest.of(0, 3);
    Page<Article> page = Page.empty(firstPageWith3Articles);

    private Logger LOG = Logger.getLogger("ArticleImplTest");


    @BeforeEach
    void setUp() throws Exception {
        LOG.info("startup");
        createArticles();

    }

    @AfterEach
    void tearDown() {
        LOG.info("teardown");
        articleRepository.deleteAll();
    }

    @Test
    void getArticles() {
        Mockito.when((articleImpl.getArticles(firstPageWith3Articles))).thenReturn((page));
        articleImpl.getArticles(firstPageWith3Articles);
        verify(articleRepository).findAll(firstPageWith3Articles);
    }

    @Test
    void createArticle() {
        Article testArticle = new Article("Article 1", "Oliver Butler", "Article one text words", today);
        Mockito.when((articleImpl.createArticle(testArticle))).thenReturn((testArticle));
        articleImpl.createArticle(testArticle);
        verify(articleRepository).save(testArticle);
    }


    private void createArticles() throws Exception {
        list.add(new Article("Article 1", "Oliver Butler", "Article one text words", today));
        list.add(new Article("Article 2", "Oliver Butler", "Article two text words", today.plusDays(1)));
        list.add(new Article("Article 3", "Oliver Butler", "Article three text words", today.plusDays(2)));
        list.add(new Article("Article 4", "Oliver Butler", "Article four text words", today.plusDays(1)));
    }

}
