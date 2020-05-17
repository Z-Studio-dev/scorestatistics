package cn.scorestatistics.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:spring/applicationContext-redis.xml")
@ServletComponentScan(basePackages = {"cn.scorestatistics.demo.filter"})
@MapperScan("cn.scorestatistics.demo.mapper")
@SpringBootApplication
public class ScoreStatisticsApplication{

    public static void main(String[] args) {
        SpringApplication.run( ScoreStatisticsApplication.class, args );
    }

}
