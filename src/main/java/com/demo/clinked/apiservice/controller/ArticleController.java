package com.demo.clinked.apiservice.controller;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.impl.ArticleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    final
    DiscoveryClient client;
    final
    ArticleImpl articleService;

    @Autowired
    public ArticleController(DiscoveryClient client, ArticleImpl articleService) {
        this.client = client;
        this.articleService = articleService;
    }

    //default get request will return page 0 with 3 results of the articles available
    @GetMapping(path = "/", produces = "application/json")
    public Page<Article> getDefaultArticles() {

        return articleService.getArticles(PageRequest.of(0, 3));

    }

    @GetMapping(path = "/{page}/{size}", produces = "application/json")
    public Page<Article> getArticles(

            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size) {

        return articleService.getArticles(PageRequest.of(page, size));

    }

    @PostMapping(path = "/", produces = "application/json")
    public Article createArticle(

            @RequestBody Article newArticle) {

        return articleService.createArticle(newArticle);
    }


}
