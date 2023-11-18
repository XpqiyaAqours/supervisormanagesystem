package com.smsys.system.mapper;

import com.smsys.system.entity.Resultlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-11-01
 */
public interface ResultlistMapper extends BaseMapper<Resultlist> {
    List<Resultlist> getResultListBySupervisorId(Integer id);

}
