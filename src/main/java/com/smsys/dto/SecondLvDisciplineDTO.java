package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondLvDisciplineDTO {
    @Excel(name = "username", orderNum = "1")
    private String username;
    @Excel(name = "secondLvDisciplineNo", orderNum = "2")
    private String secondLvDisciplineNo;
    @Excel(name = "secondLvDisciplineName", orderNum = "3")
    private String secondLvDisciplineName;
    @Excel(name = "belongFirstLvDisciplineNo", orderNum = "4")
    private String belongFirstLvDisciplineNo;
}