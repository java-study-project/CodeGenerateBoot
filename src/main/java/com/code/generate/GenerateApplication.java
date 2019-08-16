package com.code.generate;

import com.code.generate.main.GenerateMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GenerateApplication {

    private static String modulePath = "template/basic";
    public static ConfigurableApplicationContext applicationContext;

    /**
     * 通过springboot启动项目，环境变量设定modulePath，加载容器，通过容器中获取接口对象，调用接口对象创建方法来实现
     * modulePath：指定模板以及配置文件路劲
     * GenerateMain：主启动类，generate创建方法
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("modulePath", modulePath);
        applicationContext = SpringApplication.run(GenerateApplication.class, args);
        GenerateMain main = applicationContext.getBean(GenerateMain.class);
        main.generate();
    }
}
