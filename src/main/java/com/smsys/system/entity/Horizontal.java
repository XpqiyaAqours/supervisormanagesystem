package com.smsys.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@TableName("result_horizontal")
public class Horizontal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resultId;

    private String projectName;

    private String supervisorNo;

    private String income;

    private String startDate;

    private String endDate;

    private String subject;

    private String rank;

    private Byte status;

    private Byte isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSupervisorNo() {
        return supervisorNo;
    }

    public void setSupervisorNo(String supervisorNo) {
        this.supervisorNo = supervisorNo;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
        return "Horizontal{" +
            "id = " + id +
            ", resultId = " + resultId +
            ", projectName = " + projectName +
            ", supervisorNo = " + supervisorNo +
            ", income = " + income +
            ", startDate = " + startDate +
            ", endDate = " + endDate +
            ", subject = " + subject +
            ", rank = " + rank +
            ", status = " + status +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
