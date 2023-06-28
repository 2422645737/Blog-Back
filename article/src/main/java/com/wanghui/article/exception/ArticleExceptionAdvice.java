package com.wanghui.article.exception;

import com.wanghui.common.utils.R;
import com.wanghui.common.utils.ResultCodeEnum;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.wanghui.article.controller")      //集中处理异常
public class ArticleExceptionAdvice {


    //集中处理校验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String,String> error = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            error.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return R.error(ResultCodeEnum.VALID_EXCEPTION.getCode(), ResultCodeEnum.VALID_EXCEPTION.getMsg()).put("error",error);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable e){
        return R.error(ResultCodeEnum.UNKNOWN_EXCEPTION.getCode(),ResultCodeEnum.UNKNOWN_EXCEPTION.getMsg()).put("error",e.getMessage());
    }
}
