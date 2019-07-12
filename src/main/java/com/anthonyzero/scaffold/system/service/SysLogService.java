package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 异步保存操作日志
     * @param point
     * @param log
     * @throws JsonProcessingException
     */
    @Async("sysLogAsyncThreadPool")
    void saveLog(ProceedingJoinPoint point, SysLog log) throws JsonProcessingException;
}
