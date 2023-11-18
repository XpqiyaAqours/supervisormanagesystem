package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Student;
import com.smsys.system.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@RestController
@RequestMapping("/system/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping("/list")
    public Map<String, Object> getStudentList(
            @RequestParam(value = "studentNo", required = false) String studentNo,
            @RequestParam(value = "studentName", required = false) String studentName,
            @RequestParam(value = "sex", required = false) Byte sex,
            @RequestParam(value = "belongCollege", required = false) String belongCollege,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(StringUtils.hasLength(studentNo), Student::getStudentNo, studentNo)
                .eq(StringUtils.hasLength(studentName), Student::getStudentName, studentName)
                .eq(sex != null, Student::getSex, sex)
                .eq(StringUtils.hasLength(belongCollege), Student::getBelongCollege, belongCollege);

        // 执行查询
        IPage<Student> page = new Page<>(pageNo, pageSize);
        IPage<Student> result = studentService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
