package com.project.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The type Template application.
 * @author suibin.wu
 */
@SpringBootApplication
@MapperScan("com.project.template.db.mapper")
public class TemplateApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}
