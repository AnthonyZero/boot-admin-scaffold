package com.anthonyzero.scaffold.custom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("custom_demand_type")
public class DemandType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;

    /**
     * 客户ID
     */
    @TableField("custom_id")
    private Long customId;

    /**
     * 需求类型
1.社保挂靠
2.社保补缴
3.失业保险代办
4.生育保险代办
5.养老保险代办
6.工伤保险代办
7.公积金挂靠
8.公积金补缴
9.集体落户
     */
    @TableField("demand_type")
    private Integer demandType;

    @TableField("demand_type_name")
    private String demandTypeName;
}
