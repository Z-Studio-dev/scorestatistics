package cn.scorestatistics.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@ImportResource("classpath:spring/applicationContext-mvc.xml")
@ServletComponentScan(basePackages = {"cn.scorestatistics.demo.filter"})
@MapperScan("cn.scorestatistics.demo.mapper")
@EnableScheduling
@SpringBootApplication
public class ScoreStatisticsApplication{

    public static void main(String[] args) {
        SpringApplication.run( ScoreStatisticsApplication.class, args );
    }

}
