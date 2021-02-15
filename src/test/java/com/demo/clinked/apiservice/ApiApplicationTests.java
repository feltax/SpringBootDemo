package com.demo.clinked.apiservice;

import static org.assertj.core.api.Assertions.assertThat;
import com.demo.clinked.apiservice.controller.ArticleController;
import com.demo.clinked.apiservice.controller.ArticleStatsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {

    @Autowired
    private ArticleController articleController;

    @Autowired
    private ArticleStatsController statsController;


    @Test
    void contextLoads() throws Exception{
        assertThat(articleController).isNotNull();
        assertThat(statsController).isNotNull();

    }

}
