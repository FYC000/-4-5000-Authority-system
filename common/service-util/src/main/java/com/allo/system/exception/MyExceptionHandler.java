package com.allo.system.exception;

import com.allo.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author FuYiCheng
 * @date 2023-02-06 16:12
 * @description:实现异常处理类
 * @version:
 */
@RestControllerAdvice
public class MyExceptionHandler {

    //1、全局异常
    @ExceptionHandler(Exception.class)
    public Result GlobalError(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常");
    }

    //2、特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    public Result SpacialError(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行了特定异常");
    }

    //3、自定义异常类的处理
    @ExceptionHandler(MyException.class)
    public Result SpacialError(MyException e){
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}
