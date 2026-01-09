package com.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author huanghm
 * @date 2023/12/22 14:53
 */
@EnableSwagger2
@EntityScan("com.cat.entity")
@SpringBootApplication
public class CatApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CatApplication.class);
    }

}
