package com.smsys.system.mapper;

import com.smsys.system.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface PaperMapper extends BaseMapper<Paper> {
    Paper getByResultId(Integer id);

}
