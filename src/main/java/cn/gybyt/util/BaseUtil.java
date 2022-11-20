package cn.gybyt.util;

import org.springframework.lang.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * 公共工具类
 *
 * @program: utils
 * @classname: BaseUtil
 * @author: codetiger
 * @create: 2022/11/18 19:07
 **/
public class BaseUtil {

    /**
     * 解决空指针问题
     * @param t
     * @param function
     * @return
     * @param <T>
     * @param <R>
     */
    //public static <T,R>  R getByField(T t, Function<? super T, ? extends R> function) {
    //    if (BaseUtil.isNotNull(t)) {
    //        return function.apply(t);
    //    }
    //    return null;
    //}

    /**
     * 解决空指针问题,为空指定默认值
     * @param t
     * @param function
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T,R>  R getByField(T t, Function<? super T, ? extends R> function, R... defaultResult) {
        if (BaseUtil.isNotNull(t)) {
            return function.apply(t);
        }
        // 判断是否有为空时默认返回
        if (defaultResult.length > 0) {
            return defaultResult[0];
        }
        return null;
    }

    /**
     * 判断是null
     * @param o
     * @return
     */
    public static Boolean isNull(@Nullable Object o) {
        return Objects.isNull(o);
    }

    /**
     * 判断不是null
     * @param o
     * @return
     */
    public static Boolean isNotNull(@Nullable Object o) {
        return Objects.nonNull(o);
    }

}
