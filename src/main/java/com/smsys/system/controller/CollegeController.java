package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.College;
import com.smsys.system.service.ICollegeService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/system/college")
public class CollegeController {
    @Autowired
    private ICollegeService collegeService;
    @GetMapping("/list")
    public Map<String, Object> getCollegeList(
            @RequestParam(value = "collegeNo", required = false) Integer collegeNo,
            @RequestParam(value = "collegeName", required = false) String collegeName,
            @RequestParam(value = "user", required = false) String user,
            @RequestParam(value = "auditor", required = false) String auditor,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(collegeNo != null, College::getCollegeNo, collegeNo)
                .eq(StringUtils.hasLength(collegeName), College::getCollegeName, collegeName)
                .eq(StringUtils.hasLength(user), College::getUser, user)
                .eq(StringUtils.hasLength(auditor), College::getAuditor, auditor);

        // 执行查询
        IPage<College> page = new Page<>(pageNo, pageSize);
        IPage<College> result = collegeService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }


}
