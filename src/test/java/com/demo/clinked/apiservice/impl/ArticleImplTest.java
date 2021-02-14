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
    private ArticleRepository articleRepository;
    @InjectMocks
    private ArticleImpl articleImpl;
    private LocalDate today;
    private Pageable firstPageWith3Articles;
    private Page<Article> page;
    private final Logger LOG = Logger.getLogger("ArticleImplTest");


    @BeforeEach
    void setUp() throws Exception {
        LOG.info("startup");
        today = LocalDate.now();
        firstPageWith3Articles = PageRequest.of(0, 3);
        page = Page.empty(firstPageWith3Articles);
    }

    @AfterEach
    void tearDown() {
        LOG.info("teardown");

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


}
