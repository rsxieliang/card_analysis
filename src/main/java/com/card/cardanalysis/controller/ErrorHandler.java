package com.card.cardanalysis.controller;

import com.card.cardanalysis.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger logger =LoggerFactory.getLogger(ErrorHandler.class);


    @ExceptionHandler(Exception.class)
    public JsonResult ErrorException(Exception e){
        logger.error("异常===",e);
        return new JsonResult("500","服务器异常");
    }

}
