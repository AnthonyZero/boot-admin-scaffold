package com.anthonyzero.scaffold.custom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long customId;
    private String fullname;
    private String phone;
    private Integer demandType;
    private String demandTypeName;
    private Long saleUserId;
    private String saleName;
    private Integer status;
    private LocalDateTime createTime;
    private String createTimeFrom;
    private String createTimeTo;
    private LocalDateTime modifyTime;
    private String modifyUserName;
}
