package cn.gybyt.web.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * openfeign配置类
 *
 * @program: utils
 * @classname: OpenfeignConfig
 * @author: codetiger
 * @create: 2022/11/12 20:15
 **/
@Configuration
@ConditionalOnClass(Feign.class)
@EnableFeignClients
public class OpenfeignConfig {
}
