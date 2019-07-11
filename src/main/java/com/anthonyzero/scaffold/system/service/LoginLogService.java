package com.anthonyzero.scaffold.system.service;

import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-10
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 保存登录日志 更新最近一次登录时间
     * @param loginLog
     */
    void saveLoginLog(LoginLog loginLog);


    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long getTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long getTodayVisitCount();

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long getTodayIpCount();


    /**
     * 获取最近几天访问数
     * @param username
     * @param interval
     * @return
     */
    List<Map<String, Object>> getLastDaysVisitCount(String username, Integer interval);
}
