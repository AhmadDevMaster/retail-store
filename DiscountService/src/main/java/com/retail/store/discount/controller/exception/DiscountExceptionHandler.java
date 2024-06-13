package com.retail.store.discount.controller.exception;

import com.retail.store.discount.exception.CustomerTypeNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DiscountExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerTypeNotFoundException.class)
    protected ResponseEntity<Object> handleCustomerTypeNotFound(CustomerTypeNotFoundException foundException) {
        return new ResponseEntity<>(foundException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
