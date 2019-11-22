package cn.com.yusys.yusys.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface YusysIdempotent {
    String field() default "";
    String ttl() default  "";
    String repeatField() default "n";
}
