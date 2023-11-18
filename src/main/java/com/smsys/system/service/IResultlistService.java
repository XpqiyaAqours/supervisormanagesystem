package com.smsys.system.service;

import com.smsys.system.entity.Resultlist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-11-01
 */
public interface IResultlistService extends IService<Resultlist> {
    List<Resultlist> getResultListBySupervisorId(Integer id);

}
