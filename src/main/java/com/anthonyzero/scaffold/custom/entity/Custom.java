package com.anthonyzero.scaffold.custom.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Custom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableId(value = "custom_id", type = IdType.AUTO)
    private Long customId;

    /**
     * 姓名
     */
    @TableField("fullname")
    private String fullname;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 出生日期
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 客户状态0意向客户1服务中2欠费中3服务结束
     */
    @TableField("status")
    private Integer status;

    /**
     * 需求描述
     */
    @TableField("demand_desc")
    private String demandDesc;

    /**
     * 需求背景
     */
    @TableField("demand_background")
    private String demandBackground;

    /**
     * 工资基数
     */
    @TableField("wage_base")
    private BigDecimal wageBase;

    /**
     * 社保押金
     */
    @TableField("social_deposit")
    private BigDecimal socialDeposit;

    /**
     * 公积金押金
     */
    @TableField("fund_deposit")
    private BigDecimal fundDeposit;

    /**
     * 社保挂靠 是否新办社保卡
     */
    @TableField("social_rely_handle_card")
    private Integer socialRelyHandleCard;

    /**
     * 社保挂靠 社保缴费
     */
    @TableField("social_rely_pay")
    private BigDecimal socialRelyPay;

    /**
     * 社保挂靠 社保激活费
     */
    @TableField("socail_rely_active_pay")
    private BigDecimal socailRelyActivePay;

    /**
     * 社保挂靠 社保服务费
     */
    @TableField("socail_rely_service_pay")
    private BigDecimal socailRelyServicePay;

    /**
     * 社保挂靠 缴费定金
     */
    @TableField("socail_rely_down_pay")
    private BigDecimal socailRelyDownPay;

    /**
     * 社保挂靠 欠缴
     */
    @TableField("socail_rely_arrears")
    private BigDecimal socailRelyArrears;

    /**
     * 社保挂靠 合同编号
     */
    @TableField("socail_rely_contract_number")
    private String socailRelyContractNumber;

    /**
     * 社保挂靠 合同开始日期
     */
    @TableField("socail_rely_contract_start_date")
    private LocalDate socailRelyContractStartDate;

    /**
     * 社保挂靠 合同结束日期
     */
    @TableField("socail_rely_contract_end_date")
    private LocalDate socailRelyContractEndDate;

    /**
     * 社保补缴 服务费
     */
    @TableField("socail_patch_service_pay")
    private BigDecimal socailPatchServicePay;

    /**
     * 社保补缴 补缴费
     */
    @TableField("socail_patch_pay")
    private BigDecimal socailPatchPay;

    /**
     * 社保补缴 开始时间
     */
    @TableField("socail_patch_start_time")
    private LocalDate socailPatchStartTime;

    /**
     * 社保补缴 结束时间
     */
    @TableField("socail_patch_end_time")
    private LocalDate socailPatchEndTime;

    /**
     * 社保补缴 补缴几年
     */
    @TableField("socail_patch_years")
    private Integer socailPatchYears;

    /**
     * 社保补缴 缴费定金
     */
    @TableField("socail_patch_down_pay")
    private BigDecimal socailPatchDownPay;

    /**
     * 社保补缴 欠缴
     */
    @TableField("socail_patch_arrears")
    private BigDecimal socailPatchArrears;

    /**
     * 社保补缴 合同编号
     */
    @TableField("socail_patch_contract_number")
    private String socailPatchContractNumber;

    /**
     * 社保补缴 合同开始日期
     */
    @TableField("socail_patch_contract_start_date")
    private LocalDate socailPatchContractStartDate;

    /**
     * 社保补缴 合同结束日期
     */
    @TableField("socail_patch_contract_end_date")
    private LocalDate socailPatchContractEndDate;

    /**
     * 失业保险 服务费
     */
    @TableField("unemployed_service_pay")
    private BigDecimal unemployedServicePay;

    /**
     * 失业保险 定金
     */
    @TableField("unemployed_down_pay")
    private BigDecimal unemployedDownPay;

    /**
     * 失业保险 欠缴
     */
    @TableField("unemployed_arrears")
    private BigDecimal unemployedArrears;

    /**
     * 失业保险 合同编号
     */
    @TableField("unemployed_contract_number")
    private String unemployedContractNumber;

    /**
     * 失业保险 合同开始日期
     */
    @TableField("unemployed_contract_start_date")
    private LocalDate unemployedContractStartDate;

    /**
     * 失业保险 合同结束日期
     */
    @TableField("unemployed_contract_end_date")
    private LocalDate unemployedContractEndDate;

    /**
     * 生育保险 服务费
     */
    @TableField("birth_service_pay")
    private BigDecimal birthServicePay;

    /**
     * 生育保险 定金
     */
    @TableField("birth_down_pay")
    private BigDecimal birthDownPay;

    /**
     * 生育保险 欠缴
     */
    @TableField("birth_arrears")
    private BigDecimal birthArrears;

    /**
     * 生育保险 合同编号
     */
    @TableField("birth_contract_number")
    private String birthContractNumber;

    /**
     * 生育保险 合同开始日期
     */
    @TableField("birth_contract_start_date")
    private LocalDate birthContractStartDate;

    /**
     * 生育保险 合同结束日期
     */
    @TableField("birth_contract_end_date")
    private LocalDate birthContractEndDate;

    /**
     * 工伤保险 服务费
     */
    @TableField("job_service_pay")
    private BigDecimal jobServicePay;

    /**
     * 工伤保险 定金
     */
    @TableField("job_down_pay")
    private BigDecimal jobDownPay;

    /**
     * 工伤保险 欠缴
     */
    @TableField("job_arrears")
    private BigDecimal jobArrears;

    /**
     * 工伤保险 合同编号
     */
    @TableField("job_contract_number")
    private String jobContractNumber;

    /**
     * 工伤保险 合同开始日期
     */
    @TableField("job_contract_start_date")
    private LocalDate jobContractStartDate;

    /**
     * 工伤保险 合同结束日期
     */
    @TableField("job_contract_end_date")
    private LocalDate jobContractEndDate;

    /**
     * 工伤保险 其他描述
     */
    @TableField("job_other_desc")
    private String jobOtherDesc;

    /**
     * 公积金挂靠 是否新办公积金卡
     */
    @TableField("fund_rely_handle_card")
    private Integer fundRelyHandleCard;

    /**
     * 公积金挂靠 缴费
     */
    @TableField("fund_rely_pay")
    private BigDecimal fundRelyPay;

    /**
     * 公积金挂靠 激活费
     */
    @TableField("fund_rely_active_pay")
    private BigDecimal fundRelyActivePay;

    /**
     * 公积金挂靠 服务费
     */
    @TableField("fund_rely_service_pay")
    private BigDecimal fundRelyServicePay;

    /**
     * 公积金挂靠 定金
     */
    @TableField("fund_rely_down_pay")
    private BigDecimal fundRelyDownPay;

    /**
     * 公积金挂靠 欠缴
     */
    @TableField("fund_rely_arrears")
    private BigDecimal fundRelyArrears;

    /**
     * 公积金挂靠 合同编号
     */
    @TableField("fund_rely_contract_number")
    private String fundRelyContractNumber;

    /**
     * 公积金挂靠 合同开始日期
     */
    @TableField("fund_rely_contract_start_date")
    private LocalDate fundRelyContractStartDate;

    /**
     * 公积金挂靠 合同结束日期
     */
    @TableField("fund_rely_contract_end_date")
    private LocalDate fundRelyContractEndDate;

    /**
     * 公积金补缴 服务费
     */
    @TableField("fund_patch_service_pay")
    private BigDecimal fundPatchServicePay;

    /**
     * 公积金补缴 补缴费
     */
    @TableField("fund_patch_pay")
    private BigDecimal fundPatchPay;

    /**
     * 公积金补缴 定金
     */
    @TableField("fund_patch_down_pay")
    private BigDecimal fundPatchDownPay;

    /**
     * 公积金补缴 欠缴
     */
    @TableField("fund_patch_arrears")
    private BigDecimal fundPatchArrears;

    /**
     * 公积金补缴 合同编号
     */
    @TableField("fund_patch_contract_number")
    private String fundPatchContractNumber;

    /**
     * 公积金补缴 合同开始日期
     */
    @TableField("fund_patch_contract_start_date")
    private LocalDate fundPatchContractStartDate;

    /**
     * 公积金补缴 合同结束日期
     */
    @TableField("fund_patch_contract_end_date")
    private LocalDate fundPatchContractEndDate;

    /**
     * 公积金补缴 补充说明
     */
    @TableField("fund_patch_other_desc")
    private String fundPatchOtherDesc;

    /**
     * 销售人 所属人
     */
    @TableField("sale_user_id")
    private Long saleUserId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 修改人
     */
    @TableField("modify_user_id")
    private Long modifyUserId;


}
