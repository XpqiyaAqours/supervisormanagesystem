package com.smsys.system.service;

import com.smsys.system.entity.Supervisor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smsys.system.model.SupervisorDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface ISupervisorService extends IService<Supervisor> {
    SupervisorDetail getSupervisorDetail(Integer id);

}
