package cn.gybyt.util;

/**
 * @program: vue-study-java
 * @classname: BaseResponse
 * @description: 公共返回体
 * @author: Mr.Nie
 * @create: 2021/5/19 9:49
 **/
public class BaseResponse {
    String code;
    String reason;
    String msg;
    Object data;

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseResponse(String code, String reason, String msg, Object data) {
        this.code = code;
        this.reason = reason;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(String code, String reason, String msg) {
        this.code = code;
        this.reason = reason;
        this.msg = msg;
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse() {
    }

    public static BaseResponse success(String code, String msg, Object content){
        return new BaseResponse(code, msg, content);
    }

    public static BaseResponse failure(String code, String reason, String msg){
        return new BaseResponse(code,reason,msg);
    }


}
