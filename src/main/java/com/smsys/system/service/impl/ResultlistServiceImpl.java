package com.smsys.system.service.impl;

import com.smsys.system.entity.Resultlist;
import com.smsys.system.mapper.ResultlistMapper;
import com.smsys.system.service.IResultlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-11-01
 */
@Service
public class ResultlistServiceImpl extends ServiceImpl<ResultlistMapper, Resultlist> implements IResultlistService {

    @Override
    public List<Resultlist> getResultListBySupervisorId(Integer id) {
        List<Resultlist> resultlist = baseMapper.getResultListBySupervisorId(id);
        return resultlist;
    }
}
