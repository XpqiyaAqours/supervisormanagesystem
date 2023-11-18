package com.smsys.system.service.impl;

import com.smsys.system.entity.Horizontal;
import com.smsys.system.mapper.HorizontalMapper;
import com.smsys.system.service.IHorizontalService;
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
public class HorizontalServiceImpl extends ServiceImpl<HorizontalMapper, Horizontal> implements IHorizontalService {

    @Override
    public Horizontal getByResultId(Integer id) {
        Horizontal horizontal = baseMapper.getByResultId(id);
        return horizontal;
    }
}
