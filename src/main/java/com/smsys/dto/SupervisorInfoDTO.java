package com.smsys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class SupervisorInfoDTO {

    @Excel(name = "ID", orderNum = "0")
    private Integer id;

    @Excel(name = "一卡通卡号", orderNum = "1")
    private String supervisorNo;

    @Excel(name = "姓名", orderNum = "2")
    private String supervisorName;

    @Excel(name = "性别", orderNum = "3", replace = {"男_1", "女_0"})
    private int sex;

    @Excel(name = "职称", orderNum = "4")
    private String job;

    @Excel(name = "学历", orderNum = "5")
    private String educationalBackground;

    @Excel(name = "学位", orderNum = "6")
    private String degree;

    @Excel(name = "出生日期", orderNum = "7")
    private String birthDate;

    @Excel(name = "研究领域", orderNum = "8")
    private String researchAreas;

    /**
     * 0为否，1为是
     */
    @Excel(name = "是否在校外", orderNum = "9", replace = {"是_1", "否_0"})
    private int offCampus;

    @Excel(name = "荣誉称号", orderNum = "10")
    private String honoraryTitles;

    @Excel(name = "隶属学院号", orderNum = "11")
    private String belongCollege;
}