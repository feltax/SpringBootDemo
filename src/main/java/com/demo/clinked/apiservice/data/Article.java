package com.demo.clinked.apiservice.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue
    private UUID articleId;
    @Length(max = 100)
    private String articleTitle;
    private String articleAuthor;
    private String articleContent;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate articlePublishingDate;

    public UUID getArticleId() {
        return articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public LocalDate getArticlePublishingDate() {
        return articlePublishingDate;
    }

    public void setArticleId(UUID id) {
        this.articleId = id;
    }

    public void setArticleTitle(String title) {
        this.articleTitle = title;
    }

    public void setArticleAuthor(String author) {
        this.articleAuthor = author;
    }

    public void setArticleContent(String content) {
        this.articleContent = content;
    }

    public void setArticlePublishingDate(LocalDate publishingDate) {
        this.articlePublishingDate = publishingDate;
    }

    public Article(String articleTitle, String articleAuthor, String content, LocalDate publishingDate) {

        this.articleTitle = articleTitle;
        this.articleAuthor = articleAuthor;
        this.articleContent = content;
        this.articlePublishingDate = publishingDate;
    }

    public Article() {

    }


}
