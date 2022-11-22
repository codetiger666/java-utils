package cn.gybyt.web.util;

import cn.gybyt.util.BaseException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 *
 * @program: utils
 * @classname: CacheUtil
 * @author: codetiger
 * @create: 2022/11/9 19:43
 **/
public class CacheUtil {

    private static RedisTemplate redisTemplate;

    /**
     * 获取redisTemplate实例
     *
     * @return
     */
    public static RedisTemplate getRedisTemplate(){
        if (redisTemplate == null){
            redisTemplate = SpringUtil.getBean("gybytRedisTemplate");
        }
        return redisTemplate;
    }

    /**
     * 从缓存中获取数据
     *
     * @param key
     * @param type
     * @return
     * @param <T>
     */
    public static <T> T get(String key, @Nullable Class<T> type){
        return (T)getRedisTemplate().opsForValue().get(key);
    }

    /**
     * 从缓存中获取数据
     *
     * @param key
     * @return
     * @param <T>
     */
    public static <T> T get(String key){
        return (T)getRedisTemplate().opsForValue().get(key);
    }

    /**
     * 从缓存中获取哈希类型数据
     *
     * @param key
     * @param type
     * @return
     * @param <T>
     */
    public static <T> T hashGet(String key, Object hashKey, @Nullable Class<T> type){
        return (T)getRedisTemplate().opsForHash().get(key, hashKey);
    }

    /**
     * 从缓存中获取数据，如果为空使用回调获取数据
     *
     * @param key
     * @param callable
     * @return
     * @param <T>
     */
    public static <T> T get(String key, @Nullable Callable<T> callable){
        T t = (T) getRedisTemplate().opsForValue().get(key);
        if (t == null){
            try {
                t = callable.call();
                set(key, t);
            } catch (Exception e) {
                throw new BaseException(e.getMessage());
            }
        }
        return t;
    }

    /**
     * 从缓存中获取数据，如果为空使用回调获取数据，并指定缓存有效时间
     *
     * @param key
     * @param callable
     * @return
     * @param <T>
     */
    public static <T> T get(String key, @Nullable Callable<T> callable, Integer time){
        T t = (T) getRedisTemplate().opsForValue().get(key);
        if (t == null){
            try {
                t = callable.call();
                set(key, t, time);
            } catch (Exception e) {
                throw new BaseException(e.getMessage());
            }
        }
        return t;
    }

    /**
     * 从缓存中获取哈希类型数据，如果为空使用回调获取数据，并指定缓存有效时间
     *
     * @param key
     * @param callable
     * @return
     * @param <T>
     */
    public static <T> T getHash(String key, Object hashKey, @Nullable Callable<T> callable, Integer time){
        T t = (T) getRedisTemplate().opsForHash().get(key, hashKey);
        if (t == null){
            try {
                t = callable.call();
                setHash(key, hashKey, t, time);
            } catch (Exception e) {
                throw new BaseException(e.getMessage());
            }
        }
        return t;
    }

    /**
     * 从缓存中获取哈希类型数据，如果为空使用回调获取数据，并指定缓存有效时间
     *
     * @param key
     * @return
     */
    public static <T, R> Map<T, R> getHashMap(String key) {
        return (Map<T, R>) getRedisTemplate().opsForHash().entries(key);
    }

    /**
     * 从缓存中获取哈希类型数据，如果为空使用回调获取数据
     *
     * @param key
     * @param callable
     * @return
     * @param <T>
     */
    public static <T> T getHash(String key, Object hashKey, @Nullable Callable<T> callable){
        T t = (T) getRedisTemplate().opsForHash().get(key, hashKey);
        if (t == null){
            try {
                t = callable.call();
                setHash(key, hashKey, t);
            } catch (Exception e) {
                throw new BaseException(e.getMessage());
            }
        }
        return t;
    }

    /**
     * 设置哈希方式缓存，并指定缓存时间
     * @param key
     * @param object
     * @param time
     */
    public static void setHash(String key, Object hashKey, Object object, Integer time) {
        getRedisTemplate().opsForHash().put(key, hashKey, object);
        getRedisTemplate().expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 设置哈希方式批量缓存，并指定缓存时间
     * @param key
     * @param map
     * @param time
     */
    public static void setHash(String key, Map map, Integer time) {
        getRedisTemplate().opsForHash().putAll(key, map);
        getRedisTemplate().expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 设置哈希方式缓存
     * @param key
     * @param object
     */
    public static void setHash(String key, Object hashKey, Object object) {
        getRedisTemplate().opsForHash().put(key, hashKey, object);
    }

    /**
     * 设置哈希方式批量缓存
     * @param key
     * @param map
     */
    public static void setHash(String key, Map map) {
        getRedisTemplate().opsForHash().putAll(key, map);
    }

    /**
     * 从缓存中获取数据，根据类名进行转换
     *
     * @param key
     * @param className
     * @return
     * @param <T>
     */
    public static <T> T get(String key, String className){
        try {
            Class<T> aClass = (Class<T>) Class.forName(className);
            return (T)getRedisTemplate().opsForValue().get(key);
        } catch (ClassNotFoundException e) {
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 从缓存中获取哈希类型数据，根据类名进行转换
     *
     * @param key
     * @param className
     * @return
     * @param <T>
     */
    public static <T> T getHash(String key, Object hashKey, String className){
        try {
            Class<T> aClass = (Class<T>) Class.forName(className);
            return (T)getRedisTemplate().opsForHash().get(key, hashKey);
        } catch (ClassNotFoundException e) {
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 从缓存中获取哈希类型数据，根据类名进行转换
     *
     * @param key
     * @return
     * @param <T>
     */
    public static <T> T getHash(String key, Object hashKey){
        return (T)getRedisTemplate().opsForHash().get(key, hashKey);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param object
     */
    public static void set(String key, Object object){
        getRedisTemplate().opsForValue().set(key, object);
    }

    /**
     * 设置缓存，并指定缓存有效时间
     *
     * @param key
     * @param object
     * @param time
     */
    public static void set(String key, Object object, Integer time){
        getRedisTemplate().opsForValue().set(key, object, time, TimeUnit.MINUTES);
    }

    /**
     * 删除缓存
     * @param key
     */
    public static void remove(String key){
        getRedisTemplate().delete(key);
    }

    /**
     * 删除哈希缓存
     * @param key
     * @param hashKey
     */
    public static void remove(String key, Object hashKey){
        getRedisTemplate().opsForHash().delete(key, hashKey);
    }

    /**
     * 根据集合删除哈希缓存
     * @param keys
     */
    public static void remove(String key, Collection<Object> keys){
        getRedisTemplate().opsForHash().delete(key, keys);
    }

    /**
     * 根据集合删除缓存
     * @param keys
     */
    public static void remove(Collection<String> keys){
        getRedisTemplate().delete(keys);
    }

    /**
     * 判断是否有缓存
     * @param key
     * @return
     */
    public static Boolean hasKay(String key) {
        return getRedisTemplate().hasKey(key);
    }

    /**
     * 判断hash中是否有缓存
     * @param key
     * @param hashKey
     * @return
     */
    public static Boolean hasHashKey(String key, Object hashKey) {
        return getRedisTemplate().opsForHash().hasKey(key, hashKey);
    }
}
