package com.anthonyzero.scaffold.common.handler;

import com.anthonyzero.scaffold.common.core.Response;
import com.anthonyzero.scaffold.common.enums.CodeMsgEnum;
import com.anthonyzero.scaffold.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.ExpiredSessionException;
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

    /**
     * 登录认证失败
     * @param e
     * @return
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public Response handleAuthenticationException(AuthenticationException e) {
        log.error("登录认证失败", e);
        if(e instanceof UnknownAccountException) {
            return Response.error(CodeMsgEnum.INCORRECT_PASSWORD);
        } else if(e instanceof IncorrectCredentialsException) {
            return Response.error(CodeMsgEnum.INCORRECT_PASSWORD);
        } else if (e instanceof LockedAccountException) {
            return Response.error(CodeMsgEnum.LOCKED_ACCOUNT);
        }
        return Response.error(CodeMsgEnum.SERVER_ERROR);
    }

    @ExceptionHandler(value = GlobalException.class)
    public Response handleParamsInvalidException(GlobalException e) {
        log.error("系统业务自定义错误", e);
        return Response.error(e.getCodeMsgEnum());
    }

    /**
     * session过期或者已失效
     * @param e
     * @return
     */
    @ExceptionHandler(value = ExpiredSessionException.class)
    public Response handleExpiredSessionException(ExpiredSessionException e) {
        log.error("ExpiredSessionException", e);
        return Response.error(CodeMsgEnum.UNAUTHORIZED);
    }
}
