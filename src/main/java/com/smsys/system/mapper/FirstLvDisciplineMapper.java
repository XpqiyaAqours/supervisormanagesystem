package com.smsys.system.mapper;

import com.smsys.system.entity.FirstLvDiscipline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.model.FirstLvDisciplineDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface FirstLvDisciplineMapper extends BaseMapper<FirstLvDiscipline> {
    FirstLvDisciplineDetail getFirstLvDisciplineDetail(Integer id);
    List<SecondLvDiscipline> getSecondLvDisciplineInfoById(Integer id);
    FirstLvDiscipline getByNo(String no);
}
