package com.smsys.system.mapper;

import com.smsys.system.entity.Supervisor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smsys.system.model.SupervisorDetail;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface SupervisorMapper extends BaseMapper<Supervisor> {
    SupervisorDetail getSupervisorDetail(Integer id);

}