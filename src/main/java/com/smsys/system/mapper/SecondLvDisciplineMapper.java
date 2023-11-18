package com.smsys.system.mapper;

import com.smsys.system.entity.SecondLvDiscipline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smsys.system.model.SecondLvDisciplineDetail;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface SecondLvDisciplineMapper extends BaseMapper<SecondLvDiscipline> {
    SecondLvDisciplineDetail getSecondLvDisciplineDetail(Integer id);

}
