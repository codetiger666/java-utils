package cn.gybyt.util.web;

import cn.gybyt.util.BaseException;
import cn.gybyt.util.BaseResponse;
import cn.gybyt.util.NotHandleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * 返回拦截
 *
 * @program: utils
 * @classname: ControllerResponseAdvice
 * @author: codetiger
 * @create: 2022/7/20 19:19
 **/
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要对返回进行处理
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 排除自定义返回体、默认返回体、含有不处理返回注解的方法
        return !(returnType.getParameterType().isAssignableFrom(BaseResponse.class) || returnType.getParameterType().isAssignableFrom(ResponseEntity.class) || returnType.hasMethodAnnotation(NotHandleResponse.class));
    }

    /**
     * 对返回体进行处理
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(BaseResponse.success(body));
            } catch (JsonProcessingException e) {
                throw new BaseException(e.getMessage());
            }
        }
        // 否则直接包装成ResultVo返回
        return BaseResponse.success(body);
    }
}