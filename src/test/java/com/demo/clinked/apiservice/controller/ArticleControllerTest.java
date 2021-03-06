package com.demo.clinked.apiservice.controller;

import com.demo.clinked.apiservice.ApiApplication;
import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.logging.Logger;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ApiApplication.class)
class ArticleControllerTest {


    @Mock
    private ArticleImpl articleServiceImpl;

    @Mock
    private DiscoveryClient discoveryClient;

    @InjectMocks
    private ArticleController articleController;
    private final Logger LOG = Logger.getLogger("ApiApplicationImplTest");
    private Article testArticle;

    @BeforeEach
    void setUp() {
        LOG.info("startup");
         testArticle = createTestArticle();
    }
    @AfterEach
    void tearDown() {
        LOG.info("teardown");
    }

    private Article createTestArticle() {
        Article filledArticle = new Article();
        filledArticle.setArticleTitle("This is a test Title");
        filledArticle.setArticleAuthor("Author B Madeupius");
        filledArticle.setArticlePublishingDate(LocalDate.now());
        filledArticle.setArticleContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ultricies lectus et diam pellentesque, nec laoreet ipsum interdum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce porta faucibus turpis in porttitor. Phasellus varius in mauris non fringilla. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget est vel ligula rutrum condimentum. Maecenas porttitor tellus nibh, eget pretium orci bibendum a. Sed purus nisi, luctus iaculis bibendum a, facilisis quis mi. Nunc varius sed ex ut egestas. Donec sollicitudin vestibulum consequat.");
        return filledArticle;
    }

    @Test
    void getArticles() {
        Integer page = 1;
        Integer size = 3;
        Pageable firstPageWith3Articles = PageRequest.of(1, 3);
        articleController.getArticles(page, size);
        Mockito.when((articleServiceImpl.getArticles(firstPageWith3Articles))).thenReturn(null);
        verify (articleServiceImpl).getArticles(firstPageWith3Articles);
    }

    @Test
    void getDefaultArticles() {
        Pageable firstPageWith3Articles = PageRequest.of(0, 3);
        articleController.getDefaultArticles();
        Mockito.when((articleServiceImpl.getArticles(firstPageWith3Articles))).thenReturn(null);
        verify (articleServiceImpl).getArticles(firstPageWith3Articles);
    }

    @Test
    void createArticle() {
        articleController.createArticle(testArticle);
        Mockito.when((articleServiceImpl.createArticle(testArticle))).thenReturn(testArticle);
        verify (articleServiceImpl).createArticle(testArticle);
    }


}
