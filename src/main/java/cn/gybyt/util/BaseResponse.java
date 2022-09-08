package cn.gybyt.util;

/**
 * @program: vue-study-java
 * @classname: BaseResponse
 * @description: 公共返回体
 * @author: codetiger
 * @create: 2021/5/19 9:49
 **/
public class BaseResponse<T> {
    // 响应码
    Integer code;
    // 提示信息
    String message;
    // 相应数据
    T data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse() {
    }

    public BaseResponse success(T content) {
        return new BaseResponse(HttpStatusEnum.SUCCESS.value(), HttpStatusEnum.SUCCESS.reason(), content);
    }

    public BaseResponse failure(Integer code, String reason, String msg) {
        return new BaseResponse(code, reason, msg);
    }


}
