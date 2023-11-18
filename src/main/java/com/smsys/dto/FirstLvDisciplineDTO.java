package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstLvDisciplineDTO {
    @Excel(name = "username", orderNum = "1")
    private String username;
    @Excel(name = "firstLvDisciplineNo", orderNum = "2")
    private String firstLvDisciplineNo;
    @Excel(name = "firstLvDisciplineName", orderNum = "3")
    private String firstLvDisciplineName;
}
