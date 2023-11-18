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
@TableName("sys_supervisor_second_lv_discipline")
public class SupervisorSecondLvDiscipline implements Serializable {

    private static final long serialVersionUID = 1L;

    private String supervisorId;

    private String secondLvDisciplineId;

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSecondLvDisciplineId() {
        return secondLvDisciplineId;
    }

    public void setSecondLvDisciplineId(String secondLvDisciplineId) {
        this.secondLvDisciplineId = secondLvDisciplineId;
    }

    @Override
    public String toString() {
        return "SupervisorSecondLvDiscipline{" +
            "supervisorId = " + supervisorId +
            ", secondLvDisciplineId = " + secondLvDisciplineId +
        "}";
    }
}
