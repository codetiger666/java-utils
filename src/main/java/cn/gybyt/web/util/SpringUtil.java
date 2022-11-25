package cn.gybyt.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * spring 工具类
 *
 * @program: utils
 * @classname: SpringUtil
 * @author: codetiger
 * @create: 2022/11/9 19:47
 **/
@Component(value = "gybytSpringUtil")
public class SpringUtil implements ApplicationContextAware {
    private final static Logger log = LoggerFactory.getLogger(SpringUtil.class);
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }

    /**
     * 根据类获取bean
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        return context.getBean(clazz);
    }

    /**
     * 根据beanId获取对象
     * @param beanId
     * @return
     * @param <T>
     */
    public static <T> T getBean(String beanId) {
        if (beanId == null) {
            return null;
        }
        return (T) context.getBean(beanId);
    }

    /**
     * 根据beanId、类型获取对象
     * @param beanName
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (null == beanName || "".equals(beanName.trim())) {
            return null;
        }
        if (clazz == null) {
            return null;
        }
        return (T) context.getBean(beanName, clazz);
    }

    /**
     * 获取ApplicationContext对象
     * @return
     */
    public static ApplicationContext getContext() {
        if (context == null) {
            return null;
        }
        return context;
    }

    /**
     * 发布事件
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        if (context == null) {
            return;
        }
        try {
            context.publishEvent(event);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * 获取请求对象
     * @return
     */
    public static HttpServletRequest getServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取响应对象
     * @return
     */
    public static HttpServletResponse getServletResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取请求参数map
     * @return
     */
    public static Map getRequestParam() {
        HashMap<String, String> paramMap = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            paramMap.put(key, request.getParameter(key));
        }
        return paramMap;
    }

    /**
     * 获取请求体
     * @return
     */
    public static String getRequestBody() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer bodyBuffer = new StringBuffer();
        try {
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                bodyBuffer.append(line);
            }
        } catch (IOException e) {}
        return bodyBuffer.toString();
    }

    /**
     * 获取请求头
     * @return
     */
    public static Map getRequestHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            headersMap.put(key, request.getHeader(key));
        }
        return headersMap;
    }

    /**
     * 获取响应头
     * @return
     */
    public static Map getResponseHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Collection<String> headerNames = response.getHeaderNames();
        headerNames.forEach(headerName -> {
            headersMap.put(headerName, response.getHeader(headerName));
        });
        return headersMap;
    }


}
