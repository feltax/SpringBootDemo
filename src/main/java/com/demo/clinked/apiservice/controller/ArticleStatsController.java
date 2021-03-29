package com.demo.clinked.apiservice.controller;

import com.demo.clinked.apiservice.impl.ArticleStatsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/articleStats")

public class ArticleStatsController {

    final
    DiscoveryClient client;
    final
    ArticleStatsImpl articleStatsImpl;

    @Autowired
    public ArticleStatsController(DiscoveryClient client, ArticleStatsImpl articleStatsImpl) {
        this.client = client;
        this.articleStatsImpl = articleStatsImpl;
    }

    @GetMapping(path="/", produces = "application/json")
    public Map<String, Integer> getArticleStatsForWeek(){
        return articleStatsImpl.getArticleStatsForWeek(LocalDate.now());
    }
}
