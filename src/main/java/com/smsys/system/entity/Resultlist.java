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
 * @since 2023-11-01
 */
@TableName("result_resultlist")
public class Resultlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "result_id", type = IdType.AUTO)
    private Integer resultId;

    private Integer supervisorId;

    private Integer resultType;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "Resultlist{" +
            "resultId = " + resultId +
            ", supervisorId = " + supervisorId +
            ", resultType = " + resultType +
        "}";
    }
}
