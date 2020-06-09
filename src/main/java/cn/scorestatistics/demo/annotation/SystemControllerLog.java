package cn.scorestatistics.demo.annotation;

import java.lang.annotation.*;

/**
 * Controller层自定义注解，拦截Controller层
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    String description() default "";
}
