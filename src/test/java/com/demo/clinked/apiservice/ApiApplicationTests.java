package com.demo.clinked.apiservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.demo.clinked.apiservice.api.ApiApplicationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private ApiApplicationImpl impl;

    @Test
    void contextLoads() throws Exception{
        assertThat(impl).isNotNull();
    }

}
