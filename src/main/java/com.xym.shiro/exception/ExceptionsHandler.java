package com.xym.shiro.exception;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * desc
 *
 * @author xym
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ShiroException.class)//可以直接写@ExceptionHandler,不指明异常类，会自动映射
    public ModelAndView customGenericExceptionHnadler(ShiroException exception) { //还可以声明接收其他任意参数
        ModelAndView modelAndView = new ModelAndView("generic_error");
        modelAndView.addObject("errMsg", exception.getLocalizedMessage());
        return modelAndView;
    }
}