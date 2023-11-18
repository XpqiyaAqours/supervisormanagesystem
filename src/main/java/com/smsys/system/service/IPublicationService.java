package com.smsys.system.service;

import com.smsys.system.entity.Publication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IPublicationService extends IService<Publication> {
    Publication getByResultId(Integer id);

}
