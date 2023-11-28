package com.smsys.system.entity;

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
@TableName("sys_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 学生一卡通卡号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    private Byte sex;

    /**
     * 隶属学院
     */
    private String belongCollege;

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

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    //public Byte getSex() {return sex;}

    public String getSex() { return Objects.equals(sex, (byte) 1) ? "男" : "女"; }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getBelongCollege() {
        return belongCollege;
    }

    public void setBelongCollege(String belongCollege) {
        this.belongCollege = belongCollege;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id = " + id +
            ", studentNo = " + studentNo +
            ", studentName = " + studentName +
            ", sex = " + getSex() +
            ", belongCollege = " + belongCollege +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
