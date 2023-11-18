package com.smsys.system.service.impl;

import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.mapper.SecondLvDisciplineMapper;
import com.smsys.system.model.SecondLvDisciplineDetail;
import com.smsys.system.service.ISecondLvDisciplineService;
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
public class SecondLvDisciplineServiceImpl extends ServiceImpl<SecondLvDisciplineMapper, SecondLvDiscipline> implements ISecondLvDisciplineService {

    @Override
    public SecondLvDisciplineDetail getSecondLvDisciplineDetail(Integer id) {
        SecondLvDisciplineDetail secondLvDisciplineDetail = baseMapper.getSecondLvDisciplineDetail(id);
        return secondLvDisciplineDetail;
    }
}
