package com.smsys.system.service;

import com.smsys.system.entity.Paper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IPaperService extends IService<Paper> {
    Paper getByResultId(Integer id);

}
