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
@TableName("sys_first_lv_discipline")
public class FirstLvDiscipline implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
     * 一级学科点号
     */
    private String firstLvDisciplineNo;

    /**
     * 一级学科名
     */
    private String firstLvDisciplineName;

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

    public String getFirstLvDisciplineNo() {
        return firstLvDisciplineNo;
    }

    public void setFirstLvDisciplineNo(String firstLvDisciplineNo) {
        this.firstLvDisciplineNo = firstLvDisciplineNo;
    }

    public String getFirstLvDisciplineName() {
        return firstLvDisciplineName;
    }

    public void setFirstLvDisciplineName(String firstLvDisciplineName) {
        this.firstLvDisciplineName = firstLvDisciplineName;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "FirstLvDiscipline{" +
            "id = " + id +
            ", firstLvDisciplineNo = " + firstLvDisciplineNo +
            ", firstLvDisciplineName = " + firstLvDisciplineName +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
