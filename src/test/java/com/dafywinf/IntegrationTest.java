package com.dafywinf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dafywinf.core.config.EmbeddedSQL;
import com.dafywinf.hello.HelloWorldSpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

/** Base composite annotation for integration tests. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {HelloWorldSpringApplication.class})
@EmbeddedSQL
public @interface IntegrationTest {}
