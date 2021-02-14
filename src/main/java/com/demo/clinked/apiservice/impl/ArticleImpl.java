package com.demo.clinked.apiservice.impl;

import com.demo.clinked.apiservice.data.Article;
import com.demo.clinked.apiservice.repository.ArticleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class ArticleImpl implements InitializingBean {

    @Autowired
    ArticleRepository articleRepository;

    LocalDate today = LocalDate.now();

    public Page<Article> getArticles(Pageable pageable) {

        Page<Article> allArticles = articleRepository.findAll(pageable);
        return allArticles;
    }

    public Article createArticle(Article newArticle) {
        articleRepository.save(newArticle);
        return (newArticle);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        articleRepository.save(new Article("Article 1", "Oliver Butler", "Article one text words", today));
        articleRepository.save(new Article("Article 2", "Oliver Butler", "Article two text words", today.plusDays(1)));
        articleRepository.save(new Article("Article 3", "Oliver Butler", "Article three text words", today.plusDays(2)));
        articleRepository.save(new Article("Article 4", "Oliver Butler", "Article four text words", today.plusDays(1)));
    }
}
