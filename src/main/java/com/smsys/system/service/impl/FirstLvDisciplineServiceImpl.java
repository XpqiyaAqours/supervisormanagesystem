package com.smsys.system.service.impl;

import com.smsys.system.entity.FirstLvDiscipline;
import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.mapper.FirstLvDisciplineMapper;
import com.smsys.system.model.FirstLvDisciplineDetail;
import com.smsys.system.service.IFirstLvDisciplineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@Service
public class FirstLvDisciplineServiceImpl extends ServiceImpl<FirstLvDisciplineMapper, FirstLvDiscipline> implements IFirstLvDisciplineService {

    @Override
    public FirstLvDisciplineDetail getFirstLvDisciplineDetail(Integer id) {
        FirstLvDisciplineDetail firstLvDisciplineDetail = baseMapper.getFirstLvDisciplineDetail(id);
        return firstLvDisciplineDetail;
    }

    @Override
    public List<SecondLvDiscipline> getSecondLvDisciplineInfoById(Integer id) {
        List<SecondLvDiscipline> secondLvDisciplines = baseMapper.getSecondLvDisciplineInfoById(id);
        return secondLvDisciplines;
    }

    @Override
    public FirstLvDiscipline getByNo(String no) {
        FirstLvDiscipline firstLvDiscipline = baseMapper.getByNo(no);
        return firstLvDiscipline;
    }

}
