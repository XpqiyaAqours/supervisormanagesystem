package com.smsys.system.entity;

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
@TableName("sys_discipline_first_second")
public class DisciplineFirstSecond implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer firstLvDisciplineId;

    private Integer secondLvDisciplineId;

    public Integer getFirstLvDisciplineId() {
        return firstLvDisciplineId;
    }

    public void setFirstLvDisciplineId(Integer firstLvDisciplineId) {
        this.firstLvDisciplineId = firstLvDisciplineId;
    }

    public Integer getSecondLvDisciplineId() {
        return secondLvDisciplineId;
    }

    public void setSecondLvDisciplineId(Integer secondLvDisciplineId) {
        this.secondLvDisciplineId = secondLvDisciplineId;
    }

    @Override
    public String toString() {
        return "DisciplineFirstSecond{" +
            "firstLvDisciplineId = " + firstLvDisciplineId +
            ", secondLvDisciplineId = " + secondLvDisciplineId +
        "}";
    }
}
