package cn.gybyt.util;

import org.slf4j.Logger;

/**
 * 日志打印工具类
 *
 * @program: utils
 * @classname: Logger
 * @author: Codetiger
 * @create: 2022/5/14 16:31
 **/

public class LoggerUtil {
    public static void handleException(Logger log, Exception e) {
        log.error(e.getStackTrace()[0].toString() + ": " + e.getMessage());
    }


}

