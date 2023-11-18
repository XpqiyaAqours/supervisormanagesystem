package com.smsys.system.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
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
@TableName("sys_college")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Excel(name = "ID", orderNum = "0")
    private Integer id;

    @Excel(name = "用户ID", orderNum = "1")
    private Integer userId;

    @Excel(name = "学院编号", orderNum = "2")
    private Integer collegeNo;

    @Excel(name = "学院名称", orderNum = "3")
    private String collegeName;

    @Excel(name = "负责人", orderNum = "4")
    private String user;

    @Excel(name = "审核人", orderNum = "5")
    private String auditor;

    @Excel(name = "是否删除", orderNum = "6")
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

    public Integer getCollegeNo() {
        return collegeNo;
    }

    public void setCollegeNo(Integer collegeNo) {
        this.collegeNo = collegeNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "College{" +
            "id = " + id +
            ", collegeNo = " + collegeNo +
            ", collegeName = " + collegeName +
            ", user = " + user +
            ", auditor = " + auditor +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
