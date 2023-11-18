package com.smsys.system.service.impl;

import com.smsys.system.entity.Student;
import com.smsys.system.mapper.StudentMapper;
import com.smsys.system.model.StudentDetail;
import com.smsys.system.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {


    @Override
    public StudentDetail getStudentDetail(Integer id) {
        StudentDetail studentDetail = baseMapper.getStudentDetail(id);
        return studentDetail;
    }
}
