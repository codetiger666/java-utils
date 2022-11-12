package cn.gybyt.config;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * nacos配置类
 *
 * @program: utils
 * @classname: NacosConfig
 * @author: codetiger
 * @create: 2022/11/12 20:18
 **/
@Configuration
@ConditionalOnClass(NacosDiscoveryClient.class)
@EnableDiscoveryClient
public class NacosConfig {
}
