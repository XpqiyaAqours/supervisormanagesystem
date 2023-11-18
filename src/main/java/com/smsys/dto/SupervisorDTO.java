package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorDTO {
    @Excel(name = "username", orderNum = "1")
    private String username;
    @Excel(name = "supervisorNo", orderNum = "2")
    private String supervisorNo;
    @Excel(name = "supervisorName", orderNum = "3")
    private String supervisorName;
    @Excel(name = "belongCollege", orderNum = "4")
    private String belongCollege;
    @Excel(name = "belongSecondLvDisciplineNo", orderNum = "5")
    private String belongSecondLvDisciplineNo;
}