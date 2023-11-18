package com.smsys.system.service.impl;

import com.smsys.system.entity.Award;
import com.smsys.system.mapper.AwardMapper;
import com.smsys.system.service.IAwardService;
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
public class AwardServiceImpl extends ServiceImpl<AwardMapper, Award> implements IAwardService {

    @Override
    public Award getByResultId(Integer id) {
        Award award = baseMapper.getByResultId(id);
        return award;
    }
}
