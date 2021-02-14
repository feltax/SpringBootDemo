package com.demo.clinked.apiservice.api;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.Map;

@Component
@RequestMapping("/articles")
public class ApiApplicationImpl{
    @Autowired
    DiscoveryClient client;
    @Autowired
    ArticleServiceImpl articleService;

    @PostMapping("/search")
    public Page<Article> getArticles(@RequestBody Pageable pageable){
       return articleService.getArticles(pageable);
    }

    @PostMapping("/create")
    public Article createArticle(@RequestBody Article newArticle){
        return articleService.createArticle(newArticle);
    }

    @GetMapping("/Stats/{date}")
    public Map<String, Long> getArticleStatsForWeek(@PathVariable("date") LocalDate userDate){
        return articleService.getArticleStatsForWeek(userDate);
    }

    @GetMapping("/stats")
    public Map<String, Long> getArticleStatsForWeekNoDate(){
        return articleService.getArticleStatsForWeek(LocalDate.now());
    }

}
