package com.smsys.system.service.impl;

import com.smsys.system.entity.Supervisor;
import com.smsys.system.mapper.SupervisorMapper;
import com.smsys.system.model.SupervisorDetail;
import com.smsys.system.service.ISupervisorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor> implements ISupervisorService {

    @Override
    public SupervisorDetail getSupervisorDetail(Integer id) {
        SupervisorDetail supervisorDetail = baseMapper.getSupervisorDetail(id);
        return supervisorDetail;
    }


}
