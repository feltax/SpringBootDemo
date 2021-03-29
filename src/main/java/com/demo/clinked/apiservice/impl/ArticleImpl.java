package com.demo.clinked.apiservice.impl;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Component
public class ArticleImpl {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> getArticles(Pageable pageable) {

        return articleRepository.findAll(pageable);
    }

    @Transactional
    public Article createArticle(Article newArticle) {
        articleRepository.save(newArticle);
        return (newArticle);
    }


}
