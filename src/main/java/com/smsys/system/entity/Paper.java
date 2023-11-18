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
@TableName("result_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resultId;

    private String paperName;

    private String supervisorNo;

    private String firstAuthor;

    private String firstAuthorAffiliation;

    private String correspondingAuthor;

    private String source;

    private String publicationDate;

    private String grade;

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

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getSupervisorNo() {
        return supervisorNo;
    }

    public void setSupervisorNo(String supervisorNo) {
        this.supervisorNo = supervisorNo;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }

    public void setFirstAuthor(String firstAuthor) {
        this.firstAuthor = firstAuthor;
    }

    public String getFirstAuthorAffiliation() {
        return firstAuthorAffiliation;
    }

    public void setFirstAuthorAffiliation(String firstAuthorAffiliation) {
        this.firstAuthorAffiliation = firstAuthorAffiliation;
    }

    public String getCorrespondingAuthor() {
        return correspondingAuthor;
    }

    public void setCorrespondingAuthor(String correspondingAuthor) {
        this.correspondingAuthor = correspondingAuthor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        return "Paper{" +
            "id = " + id +
            ", resultId = " + resultId +
            ", paperName = " + paperName +
            ", supervisorNo = " + supervisorNo +
            ", firstAuthor = " + firstAuthor +
            ", firstAuthorAffiliation = " + firstAuthorAffiliation +
            ", correspondingAuthor = " + correspondingAuthor +
            ", source = " + source +
            ", publicationDate = " + publicationDate +
            ", grade = " + grade +
            ", rank = " + rank +
            ", status = " + status +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
