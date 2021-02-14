package com.demo.clinked.apiservice.data;

import java.time.LocalDate;
import java.util.UUID;

public class Article {
    private UUID id;
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
}
