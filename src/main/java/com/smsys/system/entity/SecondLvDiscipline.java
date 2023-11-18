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
@TableName("sys_second_lv_discipline")
public class SecondLvDiscipline implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String secondLvDisciplineNo;

    private String secondLvDisciplineName;

    private String belongFirstLvDisciplineNo;

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

    public String getSecondLvDisciplineNo() {
        return secondLvDisciplineNo;
    }

    public void setSecondLvDisciplineNo(String secondLvDisciplineNo) {
        this.secondLvDisciplineNo = secondLvDisciplineNo;
    }

    public String getSecondLvDisciplineName() {
        return secondLvDisciplineName;
    }

    public void setSecondLvDisciplineName(String secondLvDisciplineName) {
        this.secondLvDisciplineName = secondLvDisciplineName;
    }

    public String getBelongFirstLvDisciplineNo() {
        return belongFirstLvDisciplineNo;
    }

    public void setBelongFirstLvDisciplineNo(String belongFirstLvDisciplineNo) {
        this.belongFirstLvDisciplineNo = belongFirstLvDisciplineNo;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SecondLvDiscipline{" +
            "id = " + id +
            ", secondLvDisciplineNo = " + secondLvDisciplineNo +
            ", secondLvDisciplineName = " + secondLvDisciplineName +
            ", belongFirstLvDisciplineNo = " + belongFirstLvDisciplineNo +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
