package com.smsys.system.mapper;

import com.smsys.system.entity.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smsys.system.model.CollegeDetail;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface CollegeMapper extends BaseMapper<College> {

    CollegeDetail getCollegeDetail(Integer id);
}