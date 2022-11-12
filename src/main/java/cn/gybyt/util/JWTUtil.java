package cn.gybyt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 *
 * @program: utils
 * @classname: JWTUtil
 * @author: codetiger
 * @create: 2022/11/12 21:52
 **/
@Component
@ConditionalOnClass(JWT.class)
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {

    //请求头变量名
    public static String header = "Authorization";

    //token前缀
    public static String tokenPrefix = "Bearer ";

    //签名密钥(不要使用默认密钥，需要自定义)
    public static String secret = "123456";

    //有效期
    public static long expireTime = 30L;

    //存进客户端的token的key名
    public static final String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public void setHeader(String header) {
        JWTUtil.header = header;
    }

    public void setTokenPrefix(String tokenPrefix) {
        JWTUtil.tokenPrefix = tokenPrefix;
    }

    public void setSecret(String secret) {
        JWTUtil.secret = secret;
    }

    public void setExpireTime(int expireTimeInt) {
        JWTUtil.expireTime = expireTimeInt*1000L*60;
    }

    /**
     * 创建TOKEN
     * @param sub
     * @return
     */
    public static String createToken(String sub){
        return tokenPrefix + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(secret));
    }


    /**
     * 验证token
     * @param token
     */
    public static String validateToken(String token){
        try {
            return JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getSubject();
        } catch (TokenExpiredException e){
            throw new BaseException("token已经过期");
        } catch (Exception e){
            throw new BaseException("token验证失败");
        }
    }

    /**
     * 检查token是否需要更新
     * @param token
     * @return
     */
    public static boolean isNeedUpdate(String token){
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        } catch (Exception e){
            throw new BaseException("token验证失败");
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime()-System.currentTimeMillis()) < (expireTime>>1);
    }

}
