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
@TableName("result_publication")
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resultId;

    private String publicationName;

    private String supervisorNo;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版单位
     */
    private String publisher;

    /**
     * 学校署名
     */
    private String schoolAttribution;

    /**
     * 著作类别
     */
    private String category;

    /**
     * 出版时间
     */
    private String publicationDate;

    /**
     * ISBN号
     */
    private String isbn;

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

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getSupervisorNo() {
        return supervisorNo;
    }

    public void setSupervisorNo(String supervisorNo) {
        this.supervisorNo = supervisorNo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSchoolAttribution() {
        return schoolAttribution;
    }

    public void setSchoolAttribution(String schoolAttribution) {
        this.schoolAttribution = schoolAttribution;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
        return "Publication{" +
            "id = " + id +
            ", resultId = " + resultId +
            ", publicationName = " + publicationName +
            ", supervisorNo = " + supervisorNo +
            ", author = " + author +
            ", publisher = " + publisher +
            ", schoolAttribution = " + schoolAttribution +
            ", category = " + category +
            ", publicationDate = " + publicationDate +
            ", isbn = " + isbn +
            ", rank = " + rank +
            ", status = " + status +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
