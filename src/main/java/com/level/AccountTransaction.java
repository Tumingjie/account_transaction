package com.level;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.level.**"})
public class AccountTransaction {

    public static void main(String[] args) {
        //改变rocketMQ客户端的日志输出位置为当前程序的logs目录下
        System.setProperty("rocketmq.client.logRoot","logs/");
        SpringApplication.run(AccountTransaction.class, args);
    }

}
