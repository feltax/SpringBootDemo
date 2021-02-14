package com.demo.clinked.apiservice.impl;

import com.demo.clinked.apiservice.ApiApplication;
import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ApiApplication.class)
class ArticleStatsImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleStatsImpl articleStatsImpl;
    private final Logger LOG = Logger.getLogger("ArticleStatsImplTest");
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
        List<Article> articlesByPublishingDate = new ArrayList<>();
        Mockito.when((articleRepository.findArticlesByPublishingDate(date))).thenReturn(articlesByPublishingDate);

        Map<String, Integer> returnMap = articleStatsImpl.getArticleStatsForWeek(date);
        Mockito.verify(articleRepository, Mockito.times(7)).findArticlesByPublishingDate(Mockito.any(LocalDate.class));
        assert(returnMap.size() == 7);
    }
}