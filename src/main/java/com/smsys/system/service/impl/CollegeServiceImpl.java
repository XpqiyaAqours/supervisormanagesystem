package com.smsys.system.service.impl;

import com.smsys.system.entity.College;
import com.smsys.system.mapper.CollegeMapper;
import com.smsys.system.mapper.UserMapper;
import com.smsys.system.model.CollegeDetail;
import com.smsys.system.service.ICollegeService;
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
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements ICollegeService {


    @Override
    public CollegeDetail getCollegeDetail(Integer id) {
        CollegeDetail collegeDetail = baseMapper.getCollegeDetail(id);
        return collegeDetail;
    }
}