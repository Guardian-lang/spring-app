package by.ahmed.springapp.annotation;

import by.ahmed.springapp.SpringAppApplication;
import by.ahmed.springapp.SpringAppApplicationTests;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@SpringBootTest(classes = {SpringAppApplicationTests.class, SpringAppApplication.class})
@Transactional
public @interface IntegrationTesting {
}
