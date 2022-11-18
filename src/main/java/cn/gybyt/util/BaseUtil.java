package cn.gybyt.util;

import org.springframework.lang.Nullable;

import java.util.Objects;
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
    public static <T,R>  R getByField(T t, Function<? super T, ? extends R> function) {
        if (BaseUtil.isNotNull(t)) {
            return function.apply(t);
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
