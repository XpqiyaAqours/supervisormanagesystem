package com.smsys.system.mapper;

import com.smsys.system.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smsys.system.model.StudentDetail;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
public interface StudentMapper extends BaseMapper<Student> {
    StudentDetail getStudentDetail(Integer id);

}
