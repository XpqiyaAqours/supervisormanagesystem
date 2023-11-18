package com.smsys.system.service;

import com.smsys.system.entity.Patent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IPatentService extends IService<Patent> {
    Patent getByResultId(Integer id);

}
