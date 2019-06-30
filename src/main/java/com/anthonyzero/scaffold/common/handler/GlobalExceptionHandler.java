package com.anthonyzero.scaffold.common.handler;

import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return Response.error(CodeMsgEnum.SERVER_ERROR);
    }

    @ExceptionHandler(value = GlobalException.class)
    public Response handleParamsInvalidException(GlobalException e) {
        log.error("系统业务自定义错误", e);
        return Response.error(e.getCodeMsgEnum());
    }
}
