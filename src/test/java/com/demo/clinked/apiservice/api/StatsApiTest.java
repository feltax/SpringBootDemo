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
    private final Logger LOG = Logger.getLogger("ApiApplicationImplTest");
    private LocalDate date;

    @BeforeEach
    void setUp() {
        LOG.info("startup");
        date = LocalDate.now();

    }

    @AfterEach
    void tearDown() {
        LOG.info("teardown");
    }

    @Test
    void getArticleStatsForWeek() {
        statsApi.getArticleStatsForWeek();
        Mockito.when((articleStatsImpl.getArticleStatsForWeek(date))).thenReturn(null);
        verify(articleStatsImpl).getArticleStatsForWeek(date);
    }
}