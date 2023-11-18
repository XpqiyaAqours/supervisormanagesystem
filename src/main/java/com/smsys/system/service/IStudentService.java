package com.smsys.system.service;

import com.smsys.system.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smsys.system.model.StudentDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface IStudentService extends IService<Student> {
    StudentDetail getStudentDetail(Integer id);

}
