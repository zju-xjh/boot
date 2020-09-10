package com.xjh.boot.user;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication
@MapperScan("com.xjh.boot.user.dao")
//指定你的 mapper接口所在的 package

public class BootUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootUserApplication.class, args);
    }

    @RequestMapping
    public String index() {
        return "Hello Spring";
    }
}
