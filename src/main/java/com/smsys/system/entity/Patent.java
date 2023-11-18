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
@TableName("result_patent")
public class Patent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resultId;

    private String patentName;

    private String supervisorNo;

    private String firstInventor;

    private String firstInventorAffiliation;

    private String type;

    /**
     * 专利申请号
     */
    private String patentNumber;

    /**
     * 本人排名
     */
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

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getSupervisorNo() {
        return supervisorNo;
    }

    public void setSupervisorNo(String supervisorNo) {
        this.supervisorNo = supervisorNo;
    }

    public String getFirstInventor() {
        return firstInventor;
    }

    public void setFirstInventor(String firstInventor) {
        this.firstInventor = firstInventor;
    }

    public String getFirstInventorAffiliation() {
        return firstInventorAffiliation;
    }

    public void setFirstInventorAffiliation(String firstInventorAffiliation) {
        this.firstInventorAffiliation = firstInventorAffiliation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
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
        return "Patent{" +
            "id = " + id +
            ", resultId = " + resultId +
            ", patentName = " + patentName +
            ", supervisorNo = " + supervisorNo +
            ", firstInventor = " + firstInventor +
            ", firstInventorAffiliation = " + firstInventorAffiliation +
            ", type = " + type +
            ", patentNumber = " + patentNumber +
            ", rank = " + rank +
            ", status = " + status +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
