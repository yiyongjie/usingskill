package com.skill.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回类")
public class APIResponse<T> {

    /**
     * 成功
     */
    public static final String SUCCESS = "success";
    /**
     * 失败
     */
    public static final String FAIL = "fail";

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;
    /**
     * 结果
     */
    @ApiModelProperty(value = "结果")
    private String msg;

    public APIResponse() {
    }

    public APIResponse(String code, T data, String message) {
        this.code = code;
        this.data = data;
        if (message == null) {
            this.msg = ErrorTable.convertCode2LocaleMessage(code);
        } else {
            this.msg = message;
        }
    }

    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(SUCCESS, data);
    }

    public static <T> APIResponse<T> success() {
        return new APIResponse<>(SUCCESS, null);
    }

    public static <T> APIResponse<T> success(String message) {
        return new APIResponse<>(SUCCESS, null, message);
    }

    public APIResponse(String code, T data) {
        this(code, data, null);
    }

    public static APIResponse fail(String message) {
        return new APIResponse<>(FAIL, null, message);
    }

    public static APIResponse fail() {
        return new APIResponse<>(FAIL, null);
    }

    public static APIResponse fail(String code, String message) {
        return new APIResponse<>(code, null, message);
    }

    private static class ErrorTable {

        /**
         * 默认状态码
         *
         * @param code 编码
         * @return 默认返回消息
         */
        static String convertCode2LocaleMessage(String code) {
            if (SUCCESS.equalsIgnoreCase(code)) {
                return "Api调用成功";
            } else {
                return "Api调用失败";
            }
        }
    }
}
