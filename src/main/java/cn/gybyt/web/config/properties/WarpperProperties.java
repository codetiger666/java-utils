package cn.gybyt.web.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 是否包装注解
 *
 * @program: utils
 * @classname: WarpperProperties
 * @author: codetiger
 * @create: 2022/11/14 20:22
 **/
@Component
@ConfigurationProperties(prefix = "gybyt")
public class WarpperProperties {
    /**
     * 是否启用包装
     */
    private Boolean warpper;

    public Boolean getWarpper() {
        return warpper;
    }

    public void setWarpper(Boolean warpper) {
        this.warpper = warpper;
    }

}
