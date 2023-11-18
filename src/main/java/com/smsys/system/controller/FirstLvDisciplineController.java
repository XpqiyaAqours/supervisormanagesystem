package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.FirstLvDiscipline;
import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.entity.User;
import com.smsys.system.service.IFirstLvDisciplineService;
import com.smsys.system.service.ISecondLvDisciplineService;
import com.smsys.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/system/firstLvDiscipline")
public class FirstLvDisciplineController {
    @Autowired
    IFirstLvDisciplineService firstLvDisciplineService;
    //根据id查询拥有的二级学科点
    @GetMapping("/second/{id}")
    public List<SecondLvDiscipline> getSecondLvDisciplineInfoById(@PathVariable("id") Integer id){
        return firstLvDisciplineService.getSecondLvDisciplineInfoById(id);
    }
    @GetMapping("/list")
    //查询功能
    public Map<String, Object> getFirstLvDisciplineList(@RequestParam(value = "firstLvDisciplineNo", required = false) String firstLvDisciplineNo,
                                           @RequestParam(value = "firstLvDisciplineName", required = false) String firstLvDisciplineName,
                                           @RequestParam(value = "pageNo") long pageNo,
                                           @RequestParam(value = "pageSize") long pageSize) {
        System.out.println(firstLvDisciplineNo);
        LambdaQueryWrapper<FirstLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(firstLvDisciplineNo), FirstLvDiscipline::getFirstLvDisciplineNo, firstLvDisciplineNo);
        wrapper.eq(StringUtils.hasLength(firstLvDisciplineName), FirstLvDiscipline::getFirstLvDisciplineName, firstLvDisciplineName);
        wrapper.orderByDesc(FirstLvDiscipline::getId);

        Page<FirstLvDiscipline> Page = new Page<>(pageNo, pageSize);
        firstLvDisciplineService.page(Page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", Page.getTotal());
        data.put("rows", Page.getRecords());
        System.out.println(data);
        return data;

    }
}
