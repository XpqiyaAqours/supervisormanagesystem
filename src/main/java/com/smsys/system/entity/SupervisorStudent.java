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
@TableName("sys_supervisor_student")
public class SupervisorStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String supervisorId;

    private String studentId;

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "SupervisorStudent{" +
            "supervisorId = " + supervisorId +
            ", studentId = " + studentId +
        "}";
    }
}
