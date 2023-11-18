package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeDTO {
    @Excel(name = "username",orderNum = "1")
    private String username;
    @Excel(name = "collegeNo",orderNum = "2")
    private Integer collegeNo;
    @Excel(name = "collegeName",orderNum = "3")
    private String collegeName;
    @Excel(name = "user",orderNum = "4")
    private String user;
    @Excel(name = "auditor",orderNum = "5")
    private String auditor;

}
