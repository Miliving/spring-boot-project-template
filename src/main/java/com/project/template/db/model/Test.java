package com.project.template.db.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Test model
 *
 * @author suibin.wu
 */
@Data
public class Test {
    private Integer fId;

    private String fName;

    private String fMobile;

    private Integer fNumber;

    private Date fDate;

    private Byte fStatus;

    private BigDecimal fAmount;

    private String fRemark;

    private Date fCreateTime;

    private Date fModifyTime;
}