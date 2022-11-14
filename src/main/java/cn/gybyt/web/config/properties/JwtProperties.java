package cn.gybyt.web.config.properties;

import com.auth0.jwt.JWT;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt变量提示
 *
 * @program: utils
 * @classname: JWTProperties
 * @author: codetiger
 * @create: 2022/11/14 21:09
 **/
@Component
@ConditionalOnClass(JWT.class)
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 请求头变量名
     */
    private String header = "Authorization";

    /**
     * token前缀
     */
    private String tokenPrefix = "Bearer ";

    /**
     * 签名密钥
     */
    private String secret = "123456";

    /**
     * 有效期(分钟)
     */
    private Integer expireTime = 30;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }
}
