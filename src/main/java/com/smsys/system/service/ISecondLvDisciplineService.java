package com.smsys.system.service;

import com.smsys.system.entity.SecondLvDiscipline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smsys.system.model.SecondLvDisciplineDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface ISecondLvDisciplineService extends IService<SecondLvDiscipline> {
    SecondLvDisciplineDetail getSecondLvDisciplineDetail(Integer id);

}
