package by.ahmed.springapp.http.controller.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "by.ahmed.springapp.greeting.controller.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

}