package cn.gybyt.util.web;

import cn.gybyt.util.BaseException;
import cn.gybyt.util.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器请求页面
 * @program: utils
 * @classname: FilterErrorController
 * @author: codetiger
 * @create: 2022/8/7 15:30
 **/
@RestController
public class FilterErrorController{


    @RequestMapping("/error/filter")
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public BaseResponse error(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        BaseException baseException = (BaseException) request.getAttribute("filterError");
        return new BaseResponse(403, baseException.getMessage());
    }

}