package cn.gybyt.util;


/**
 * 公共错误
 *
 * @program: vue-study-java
 * @classname: BaseException
 * @author: codetiger
 * @create: 2021/5/19 11:39
 **/

public class BaseException extends RuntimeException {
    int code;
    String msg;

    /**
     * 默认构造方法
     */
    public BaseException() {
    }

    /**
     * 错误信息，默认500错误
     * @param message
     */
    public BaseException(String  message){
        super(message);
        this.code = 500;
        this.msg = message;
    }
    /**
     * @param message 错误信息
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    /**
     * @param code    错误码
     * @param message 错误信息
     * @return
     * @Author codetiger
     * @Description //TODO
     * @Date 22:39 2022/5/15
     * @Param
     **/
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
