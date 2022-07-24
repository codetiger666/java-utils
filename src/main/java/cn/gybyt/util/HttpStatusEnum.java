package cn.gybyt.util;

/**
 * @program: vue-study-java
 * @classname: HttpStatusEnum
 * @description: http状态码枚举
 * @author: Mr.Nie
 * @create: 2021/5/19 14:39
 **/
public enum HttpStatusEnum {
    SUCCESS(200, "成功"),
    UNAUTHORIZED(401, "未授权"),
    NOTFOUND(404, "资源未找到"),
    USERNOTEXIST(1001, "用户不存在!"),
    USERNAMEPASSWORDNOTMATCH(1002, "用户名或密码错误"),
    USERLOGINSUCCESS(1003, "登录成功!");


    private final int value;
    private final String reason;

    HttpStatusEnum(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int value() {
        return this.value;
    }

    public String reason() {
        return this.reason;
    }
}
