package com.demo.clinked.apiservice.data;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Article {
    @Id
    private UUID id;
    @Length(max = 100)
    private String title;
    private String author;
    private String content;
    private LocalDate publishingDate;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Article(String title, String author, String content, LocalDate publishingDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.content = content;
        this.publishingDate = publishingDate;
    }

    public Article() {
        this.id = UUID.randomUUID();
    }


}
