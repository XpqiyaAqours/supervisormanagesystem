package com.smsys.system.service.impl;

import com.smsys.system.entity.Vertical;
import com.smsys.system.mapper.VerticalMapper;
import com.smsys.system.service.IVerticalService;
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
public class VerticalServiceImpl extends ServiceImpl<VerticalMapper, Vertical> implements IVerticalService {

    @Override
    public Vertical getByResultId(Integer id) {
        Vertical vertical = baseMapper.getByResultId(id);
        return vertical;
    }
}
