package com.anthonyzero.scaffold.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOW = "2";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "123456";
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 联系电话
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    @TableField("status")
    private String status;

    /**
     * 性别 0男 1女 2保密
     */
    @TableField("sex")
    private String sex;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 备注描述
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 主题
     */
    @TableField("theme")
    private String theme;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 最近登录时间
     */
    @TableField("last_login_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private String roleId;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * redis cache使用
     * @return
     */
    public Long getId() {
        return userId;
    }

    //用于查询字段
    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;
}
