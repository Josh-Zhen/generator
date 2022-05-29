package com.moonlit.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        log.info("月光生成器——启动成功！");
    }

}
