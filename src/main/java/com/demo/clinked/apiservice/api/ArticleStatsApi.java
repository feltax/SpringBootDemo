package com.demo.clinked.apiservice.api;

import com.demo.clinked.apiservice.impl.ArticleImpl;
import com.demo.clinked.apiservice.impl.ArticleStatsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/articleStats")
public class ArticleStatsApi {

    @Autowired
    DiscoveryClient client;
    @Autowired
    ArticleStatsImpl articleStatsImpl;

    @GetMapping(path="/", produces = "application/json")
    public Map<String, Integer> getArticleStatsForWeek(){
        return articleStatsImpl.getArticleStatsForWeek(LocalDate.now());
    }
}
