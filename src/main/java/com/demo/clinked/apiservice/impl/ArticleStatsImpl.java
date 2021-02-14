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

    @Autowired
    ArticleRepository articleRepository;

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
