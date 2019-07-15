package com.anthonyzero.scaffold.system.mapper;

import com.anthonyzero.scaffold.system.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-10
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {

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
     * 获取系统今日访问 不同IP数量
     *
     * @return Long
     */
    Long getTodayIpCount();


    /**
     * 获取系统近10天来的访问记录
     *
     * @param userName 用户账号 可选
     * @return 系统近10天来的访问记录
     */
    List<Map<String, Object>> getLastDaysVisitCount(@Param("username") String userName);

}
