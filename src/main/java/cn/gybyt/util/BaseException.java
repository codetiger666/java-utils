package cn.gybyt.util;


/**
 * 公共错误
 * @program: vue-study-java
 * @classname: BaseException
 * @author: codetiger
 * @create: 2021/5/19 11:39
 **/

public class BaseException extends RuntimeException{
    String code;
    String msg;

    /**
     * 默认构造方法
     */
    public BaseException(){
    }

    /**
     *
     * @param message 错误信息
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @Author codetiger
     * @Description //TODO
     * @Date 22:39 2022/5/15
     * @Param
     * @param code 错误码
     * @param message 错误信息
     * @return
     **/
    public BaseException(String code,String message) {
        super(message);
        this.code=code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
