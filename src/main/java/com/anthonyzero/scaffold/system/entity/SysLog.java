package com.anthonyzero.scaffold.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anthonyzero
 * @since 2019-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 操作用户
     */
    @TableField("username")
    private String username;

    /**
     * 操作内容
     */
    @TableField("operation")
    private String operation;

    /**
     * 耗时
     */
    @TableField("time")
    private BigDecimal time;

    /**
     * 方法
     */
    @TableField("method")
    private String method;

    /**
     * 方法参数
     */
    @TableField("params")
    private String params;

    /**
     * 操作者IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
