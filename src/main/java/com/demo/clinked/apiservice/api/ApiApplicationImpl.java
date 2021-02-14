package com.demo.clinked.apiservice.api;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Component
@RequestMapping("/articles")
public class ApiApplicationImpl{
    @Autowired
    DiscoveryClient client;
    @Autowired
    ArticleServiceImpl articleService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getArticles")
    public List<Article> getArticles(){
       return articleService.getArticles();
    }

    @PostMapping("/createArticle")
    public Article createArticle(@RequestBody Article newArticle){
        return articleService.createArticle(newArticle);
    }

    @GetMapping("/getArticleStatsForWeek/{date}")
    public Map<String, Integer> getArticleStatsForWeek(@PathVariable("date") LocalDate userDate){
        return articleService.getArticleStatsForWeek(userDate);
    }


}
