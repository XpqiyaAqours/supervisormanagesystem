package com.smsys.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@TableName("sys_supervisor")
public class Supervisor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "ID", orderNum = "0")
    private Integer id;

    private Integer userId;

    /**
     * 导师一卡通卡号
     */
    @Excel(name = "一卡通卡号", orderNum = "1")
    private String supervisorNo;
    @Excel(name = "姓名", orderNum = "2")
    private String supervisorName;
    @Excel(name = "性别", orderNum = "3", replace = {"男_1", "女_0"})
    private Byte sex;
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
    @Excel(name = "是否在校外", orderNum = "9")
    private Byte offCampus;
    @Excel(name = "荣誉称号", orderNum = "10")
    private String honoraryTitles;
    @Excel(name = "隶属学院号", orderNum = "11")
    private String belongCollege;

    private String belongSecondLvDisciplineNo;

    /**
     * 0为未审核，1为未通过，2为通过
     */
    private Byte researchProjectsCheck;

    /**
     * 0为未审核，1为未通过，2为通过
     */
    private Byte researchResultsCheck;

    /**
     * 0为未审核，1为未通过，2为通过
     */
    private Byte graduateSchoolCheck;

    /**
     * 0为未审核，1为未通过，2为通过
     */
    private Byte status;

    private Byte isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSupervisorNo() {
        return supervisorNo;
    }

    public void setSupervisorNo(String supervisorNo) {
        this.supervisorNo = supervisorNo;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getSexStr() {
        return Objects.equals(sex, (byte) 1) ? "男" : "女";
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducationalBackground() {
        return educationalBackground;
    }

    public void setEducationalBackground(String educationalBackground) {
        this.educationalBackground = educationalBackground;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(String researchAreas) {
        this.researchAreas = researchAreas;
    }

    public Byte getOffCampus() {
        return offCampus;
    }

    public void setOffCampus(Byte offCampus) {
        this.offCampus = offCampus;
    }

    public String getOffCampusStr() {
        return Objects.equals(sex, (byte) 1) ? "是" : "否";
    }

    public String getHonoraryTitles() {
        return honoraryTitles;
    }

    public void setHonoraryTitles(String honoraryTitles) {
        this.honoraryTitles = honoraryTitles;
    }

    public String getBelongCollege() {
        return belongCollege;
    }

    public void setBelongCollege(String belongCollege) {
        this.belongCollege = belongCollege;
    }

    public String getBelongSecondLvDisciplineNo() {
        return belongSecondLvDisciplineNo;
    }

    public void setBelongSecondLvDisciplineNo(String belongSecondLvDisciplineNo) {
        this.belongSecondLvDisciplineNo = belongSecondLvDisciplineNo;
    }

    public Byte getResearchProjectsCheck() {
        return researchProjectsCheck;
    }

    public void setResearchProjectsCheck(Byte researchProjectsCheck) {
        this.researchProjectsCheck = researchProjectsCheck;
    }

    public Byte getResearchResultsCheck() {
        return researchResultsCheck;
    }

    public void setResearchResultsCheck(Byte researchResultsCheck) {
        this.researchResultsCheck = researchResultsCheck;
    }

    public Byte getGraduateSchoolCheck() {
        return graduateSchoolCheck;
    }

    public void setGraduateSchoolCheck(Byte graduateSchoolCheck) {
        this.graduateSchoolCheck = graduateSchoolCheck;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
            "id = " + id +
            ", supervisorNo = " + supervisorNo +
            ", supervisorName = " + supervisorName +
            ", sex = " + sex +
            ", job = " + job +
            ", educationalBackground = " + educationalBackground +
            ", degree = " + degree +
            ", birthDate = " + birthDate +
            ", researchAreas = " + researchAreas +
            ", offCampus = " + offCampus +
            ", honoraryTitles = " + honoraryTitles +
            ", belongCollege = " + belongCollege +
            ", basicInformationCheck = " + belongSecondLvDisciplineNo +
            ", researchProjectsCheck = " + researchProjectsCheck +
            ", researchResultsCheck = " + researchResultsCheck +
            ", graduateSchoolCheck = " + graduateSchoolCheck +
            ", status = " + status +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
