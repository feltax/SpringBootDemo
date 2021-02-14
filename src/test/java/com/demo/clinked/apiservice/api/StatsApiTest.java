package com.demo.clinked.apiservice.api;

import com.demo.clinked.apiservice.ApiApplication;
import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleImpl;
import com.demo.clinked.apiservice.impl.ArticleStatsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ApiApplication.class)
class StatsApiTest {
    @Mock
    private ArticleStatsImpl articleStatsImpl;

    @Mock
    private DiscoveryClient discoveryClient;

    @InjectMocks
    private ArticleStatsApi statsApi;

    private Logger LOG = Logger.getLogger("ApiApplicationImplTest");
    private LocalDate date;
    private Article testArticle;

    @BeforeEach
    void setUp() {
        LOG.info("startup");
        date = LocalDate.now();
        testArticle = createTestArticle();
    }

    @AfterEach
    void tearDown() {
        LOG.info("teardown");
    }

    private Article createTestArticle() {
        Article filledArticle = new Article();
        filledArticle.setTitle("This is a test Title");
        filledArticle.setAuthor("Author B Madeupius");
        filledArticle.setPublishingDate(LocalDate.now().plusDays(1));
        filledArticle.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ultricies lectus et diam pellentesque, nec laoreet ipsum interdum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce porta faucibus turpis in porttitor. Phasellus varius in mauris non fringilla. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget est vel ligula rutrum condimentum. Maecenas porttitor tellus nibh, eget pretium orci bibendum a. Sed purus nisi, luctus iaculis bibendum a, facilisis quis mi. Nunc varius sed ex ut egestas. Donec sollicitudin vestibulum consequat.");

        return filledArticle;
    }

    @Test
    void getArticleStatsForWeek() {
        statsApi.getArticleStatsForWeek();
        Mockito.when((articleStatsImpl.getArticleStatsForWeek(date))).thenReturn(null);
        verify(articleStatsImpl).getArticleStatsForWeek(date);
    }
}