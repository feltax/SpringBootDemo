package com.demo.clinked.apiservice.api;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleApi {

    @Autowired
    DiscoveryClient client;
    @Autowired
    ArticleImpl articleService;

    @GetMapping(path="/{page}/{size}", produces = "application/json")
    public Page<Article> getArticles(@PathVariable("page") int page, @PathVariable("size") int size){
       return articleService.getArticles(PageRequest.of(page, size));
    }

    @PostMapping(path="/", produces = "application/json")
    public Article createArticle(@RequestBody Article newArticle){
        return articleService.createArticle(newArticle);
    }


}
