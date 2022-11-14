package cn.gybyt.web.config;

import org.springframework.aop.framework.AopContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 切面配置类
 *
 * @program: utils
 * @classname: AdviceConfig
 * @author: codetiger
 * @create: 2022/11/12 12:31
 **/

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(AopContext.class)
@EnableAspectJAutoProxy
public class AdviceConfig {
}
