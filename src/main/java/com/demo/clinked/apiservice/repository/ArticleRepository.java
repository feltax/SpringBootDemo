package com.demo.clinked.apiservice.repository;

import com.demo.clinked.apiservice.data.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

    List<Article> getAllBy(Pageable pageable);
    Long countByPublishingDate(LocalDate date);
    List<Article> findArticlesByPublishingDate(LocalDate date);
    Article findByTitle(String title);
}