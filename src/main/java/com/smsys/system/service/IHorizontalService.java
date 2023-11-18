package com.smsys.system.service;

import com.smsys.system.entity.Horizontal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IHorizontalService extends IService<Horizontal> {
    Horizontal getByResultId(Integer id);

}
