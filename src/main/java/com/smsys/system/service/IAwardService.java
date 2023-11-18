package com.smsys.system.service;

import com.smsys.system.entity.Award;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IAwardService extends IService<Award> {
    Award getByResultId(Integer id);

}
