package cn.gybyt.web.advice;

import cn.gybyt.util.BaseException;
import cn.gybyt.util.BaseResponse;
import cn.gybyt.web.util.HttpStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    /**
     * 处理web全局异常
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity MethodArgumentNotValidExceptionHandler(Exception e) {
        // 设置响应为JSON格式
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        log.error(e.getMessage(), e);
        // 返回异常信息
        return new ResponseEntity(BaseResponse.failure(500, e.getMessage()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity APIExceptionHandler(BaseException e) {
        // 设置响应为JSON格式
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        // 打印错误信息
        log.error(e.getMsg(), e);
        // 转换状态码为HttpStatus对象
        HttpStatus httpStatus = HttpStatus.resolve(e.getCode());
        new ResponseEntity(BaseResponse.failure(HttpStatusEnum.SERVERERROR.value(), e.getMsg()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        // 返回自定义状态码返回体
        if (httpStatus == null){
            return new ResponseEntity(BaseResponse.failure(HttpStatusEnum.SERVERERROR.value(), e.getMsg()), headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(BaseResponse.failure(e.getCode(), e.getMsg()), headers, httpStatus);
    }
}
