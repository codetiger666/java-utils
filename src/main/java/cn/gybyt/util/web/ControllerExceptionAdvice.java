package cn.gybyt.util.web;

import cn.gybyt.util.BaseException;
import cn.gybyt.util.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 异常拦截
 *
 * @program: utils
 * @classname: ControllerExceptionAdvice
 * @author: codetiger
 * @create: 2022/7/20 19:29
 **/
@RestControllerAdvice
public class ControllerExceptionAdvice {
    private final Logger log = LoggerFactory.getLogger(ControllerExceptionAdvice.class);
    @ExceptionHandler({BindException.class})
    public BaseResponse MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.error(objectError.getDefaultMessage());
        return new BaseResponse(500 , objectError.getDefaultMessage());
    }
    @ExceptionHandler(BaseException.class)
    public BaseResponse APIExceptionHandler(BaseException e) {
        log.error(e.getMessage());
        return new BaseResponse(e.getCode(), e.getMessage());
    }
}
