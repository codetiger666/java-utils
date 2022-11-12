package cn.gybyt.advice;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 日志打印切面
 *
 * @program: utils
 * @classname: LogAdvice
 * @author: codetiger
 * @create: 2022/11/12 12:39
 **/
@Component
@Aspect
public class LogAdvice {
    private final Logger log = LoggerFactory.getLogger(LogAdvice.class);

    /**
     * 切面异常处理，此处全部拦截需要排除spring、jdk自身包，防止循环代理
     *
     * @param e
     */
    @AfterThrowing(pointcut = "execution(* *..*.*(..)) && !execution(* org.springframework..*.*(..)) && !execution(* java*..*.*(..)) && !execution(* com.sun..*.*(..))", throwing = "e")
    public void throwAdviceLog(Throwable e) {
        // 获取异常堆栈信息
        StackTraceElement[] stackTrace = e.getStackTrace();
        // 记录异常的层数
        int post = 0;
        // 匹配需要的信息
        String pattern = ".*\\.java:\\d*\\)";
        // 匹配跳出循环条件
        String patternEnd = ".*\\$\\$.*";
        for (StackTraceElement stackTraceElement : stackTrace) {
            // 判断是否符合需要的条件
            boolean matches = Pattern.matches(pattern, stackTraceElement.toString());
            if (matches) {
                post++;
            }
            // 判断何时跳出循环
            boolean matchesEnd = Pattern.matches(patternEnd, stackTraceElement.toString());
            if (matchesEnd) {
                break;
            }
        }
        // 对需要输出的堆栈信息进行打印
        for (int i = 0; i < post; i++) {
            log.info(stackTrace[i].toString());
        }
    }
}
