package com.demo.clinked.apiservice.impl;
import com.demo.clinked.apiservice.data.Article;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ArticleServiceImpl implements InitializingBean {

    List<Article> articleList;
    LocalDate today = LocalDate.now();
    public List<Article> getArticles(){
        return articleList;
    }

    public Article createArticle(Article newArticle){
        articleList.add(newArticle);
        return newArticle;
    }

    public Map<String, Integer> getArticleStatsForWeek(LocalDate date){
        Map<String, Integer> dayCountMap = new HashMap<>();
        int i = 0;
            while (i<7){
           dayCountMap.put(String.valueOf(date.getDayOfWeek()), countArticlesPublishedOnDate(date));
           i++;
       }
        return null;
    }

    private Integer countArticlesPublishedOnDate(LocalDate date) {
        Integer count = 0;
        //if you have a database you'd just use an sql query with Count
        for (Article article: articleList) {
            if (article.getPublishingDate().isEqual(date)){
                count++;
            }
        }
        return count;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        articleList.add(new Article("Article 1", "Oliver Butler", "Article one text words", today));
        articleList.add(new Article("Article 2", "Oliver Butler", "Article two text words",  today.plusDays(1)));
        articleList.add(new Article("Article 3", "Oliver Butler", "Article three text words", today.plusDays(2)));
        articleList.add(new Article("Article 4", "Oliver Butler", "Article four text words", today.plusDays(1)));
    }
}
