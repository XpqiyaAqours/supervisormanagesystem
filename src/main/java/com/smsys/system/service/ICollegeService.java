package com.smsys.system.service;

import com.smsys.system.entity.College;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smsys.system.mapper.CollegeMapper;
import com.smsys.system.model.CollegeDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface ICollegeService extends IService<College> {
    CollegeDetail getCollegeDetail(Integer id);
}