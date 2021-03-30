package com.demo.clinked.apiservice.impl;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ArticleStatsImpl {

    final ArticleRepository articleRepository;

    @Autowired
    public ArticleStatsImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //returns a hashmap of <date(str), int> for the count of articles published on each day
    //Note: date used for comparison disregards time (LocalDate vs LocalDateTime), see /config/DateConfig.class
    public LinkedHashMap<String, Integer> getArticleStatsForWeek(LocalDate date) {
        LinkedHashMap<String, Integer> dayCountMap = new LinkedHashMap<>();
        int i = 0;
        while (i < 7) {
            dayCountMap.put(String.valueOf(date), countArticlesPublishedOnDate(date));
            date = date.plusDays(1);
            i++;
        }
        return dayCountMap;
    }

    private Integer countArticlesPublishedOnDate(LocalDate date) {
        List<Article> articlesByPublishingDate = articleRepository.findArticlesByPublishingDate(date);
        return articlesByPublishingDate.size();
    }


}
