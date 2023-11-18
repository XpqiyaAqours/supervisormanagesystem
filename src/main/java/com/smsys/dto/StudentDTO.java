package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @Excel(name = "username", orderNum = "1")
    private String username;
    @Excel(name = "studentNo", orderNum = "2")
    private String studentNo;
    @Excel(name = "studentName", orderNum = "3")
    private String studentName;
    @Excel(name = "sex", orderNum = "4", replace = {"男_1", "女_0"})
    private int sex;
    @Excel(name = "belongCollege", orderNum = "5")
    private String belongCollege;

}
