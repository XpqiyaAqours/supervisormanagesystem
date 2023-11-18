package com.smsys.system.service;

import com.smsys.system.entity.FirstLvDiscipline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.model.FirstLvDisciplineDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IFirstLvDisciplineService extends IService<FirstLvDiscipline> {
    FirstLvDisciplineDetail getFirstLvDisciplineDetail(Integer id);
    List<SecondLvDiscipline> getSecondLvDisciplineInfoById(Integer id);
    FirstLvDiscipline getByNo(String no);

}
