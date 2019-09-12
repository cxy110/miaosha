package com.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MiaoshaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MiaoshaApplication.class, args);
  }

}
