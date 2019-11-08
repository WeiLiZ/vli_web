package com.vli.po;

import java.util.ArrayList;
import java.util.List;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    /* 失败状态码 */
    FAILURE(0, "失败"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    PICTURE_UPLOAD_TYPE_ERROR(10005, "上传图片格式错误"),
    PICTURE_UPLOAD_NULL_ERROR(10006, "上传图片为空"),
    PICTURE_UPLOAD_ERROR_ERROR(10007, "上传图片错误"),
    PICTURE_UPLOAD_TOBIG_ERROR(10008, "上传图片大于10m"),
    CAPTCHA_ERROR(10009, "图片验证码错误"),

    FILE_UPLOAD_NULL_ERROR(10009, "上传文件为空"),
    FILE_UPLOAD_ERROR_ERROR(10010, "上传文件错误。"),
    FILE_UPLOAD_TOBIG_ERROR(10011, "上传文件错误。文件超过规定大小"),
    FILE_UPLOAD_TYPE_ERROR(10012, "上传文件格式错误"),


    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_HAS_LOGIN(20006, "用户已登录"),
    USER_FORVER(20007, "该账户已被永久封停"),
    USER_MOMENT(20008, "该账户已被暂时封停"),
    USER_HAS_USERNAME_OR_PHONE(20009, "用户名或手机号"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "权限不足");

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
