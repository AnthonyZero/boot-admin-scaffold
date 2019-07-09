package com.anthonyzero.scaffold.common.core;

import com.alibaba.fastjson.JSON;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import lombok.Getter;

/**
 * 统一消息返回
 */
@Getter
public class Response<T> {
    private static final int DEFAULT_SUCCESS_CODE = 0;
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private int code;
    private String message;
    private T data;

    private Response() {
        this.code = DEFAULT_SUCCESS_CODE;
        this.message = DEFAULT_SUCCESS_MESSAGE;
    }

    private Response(T data) {
        this.code = DEFAULT_SUCCESS_CODE;
        this.message = DEFAULT_SUCCESS_MESSAGE;
        this.data = data;
    }

    private Response(CodeMsgEnum codeMsgEnum) {
        if (codeMsgEnum == null){
            return;
        }
        this.code = codeMsgEnum.getCode();
        this.message = codeMsgEnum.getMsg();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * 默认成功的时候调用
     * @param <T>
     * @return
     */
    public static <T> Response<T> success() {
        return new Response<T>();
    }

    /**
     * 成功时候调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data){
        return new Response<T>(data);
    }

    /**
     * 失败时候调用
     * @param codeMsgEnum
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(CodeMsgEnum codeMsgEnum){
        return new Response<T>(codeMsgEnum);
    }
}
