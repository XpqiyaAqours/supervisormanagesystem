package com.smsys.system.service.impl;

import com.smsys.system.entity.Patent;
import com.smsys.system.mapper.PatentMapper;
import com.smsys.system.service.IPatentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class PatentServiceImpl extends ServiceImpl<PatentMapper, Patent> implements IPatentService {

    @Override
    public Patent getByResultId(Integer id) {
        Patent patent = baseMapper.getByResultId(id);
        return patent;
    }
}
