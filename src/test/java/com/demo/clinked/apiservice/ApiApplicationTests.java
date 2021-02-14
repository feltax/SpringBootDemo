package com.demo.clinked.apiservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.demo.clinked.apiservice.api.ArticleApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private ArticleApi impl;

    @Test
    void contextLoads() throws Exception{
        assertThat(impl).isNotNull();
    }

}
