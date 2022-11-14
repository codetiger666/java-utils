package cn.gybyt.web.util;

import cn.gybyt.util.BaseException;
import cn.gybyt.web.config.properties.JwtProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;

/**
 * jwt工具类
 *
 * @program: utils
 * @classname: JWTUtil
 * @author: codetiger
 * @create: 2022/11/12 21:52
 **/
public class JwtUtil {

    private static JwtProperties jwtProperties;

    public static JwtProperties getJwtProperties(){
        if (jwtProperties == null){
            jwtProperties = SpringUtil.getBean("jwtProperties");
        }
        return jwtProperties;
    }

    /**
     * 创建TOKEN
     * @param sub
     * @return
     */
    public static String createToken(String sub){
        return getJwtProperties().getTokenPrefix() + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + getJwtProperties().getExpireTime()))
                .sign(Algorithm.HMAC512(getJwtProperties().getSecret()));
    }


    /**
     * 验证token
     * @param token
     */
    public static String validateToken(String token){
        try {
            return JWT.require(Algorithm.HMAC512(getJwtProperties().getSecret()))
                    .build()
                    .verify(token.replace(getJwtProperties().getTokenPrefix(), ""))
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
            expiresAt = JWT.require(Algorithm.HMAC512(getJwtProperties().getSecret()))
                    .build()
                    .verify(token.replace(getJwtProperties().getTokenPrefix(), ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        } catch (Exception e){
            throw new BaseException("token验证失败");
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime()-System.currentTimeMillis()) < (getJwtProperties().getExpireTime()>>1);
    }

}
