package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.FirstLvDiscipline;
import com.smsys.system.entity.SecondLvDiscipline;
import com.smsys.system.entity.Supervisor;
import com.smsys.system.entity.SupervisorSecondLvDiscipline;
import com.smsys.system.service.IFirstLvDisciplineService;
import com.smsys.system.service.ISecondLvDisciplineService;
import com.smsys.system.service.ISupervisorSecondLvDisciplineService;
import com.smsys.system.service.ISupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlx
 * @since 2023-10-29
 */
@RestController
@RequestMapping("/system/secondlvdiscipline")
public class SecondLvDisciplineController {
    @Autowired
    private IFirstLvDisciplineService firstLvDisciplineService;
    @Autowired
    private ISecondLvDisciplineService secondLvDisciplineService;
    //根据二级学院id获取所属一级学院
    @GetMapping("/first/{id}")
    public FirstLvDiscipline getFirstLvDisciplineById(@PathVariable("id") Integer id){
        SecondLvDiscipline secondLvDiscipline = new SecondLvDiscipline();
        secondLvDiscipline = secondLvDisciplineService.getById(id);
        System.out.println(secondLvDiscipline);
        System.out.println(secondLvDiscipline.getBelongFirstLvDisciplineNo());
        FirstLvDiscipline data = firstLvDisciplineService.getByNo(secondLvDiscipline.getBelongFirstLvDisciplineNo());
        return data;
    }
    @Autowired
    private ISupervisorService supervisorService;
    @Autowired
    private ISupervisorSecondLvDisciplineService supervisorSecondLvDisciplineService;
    //根据二级学院id获取拥有的导师名单
    @GetMapping("/supervisor/{id}")
    public List<Supervisor> getSupervisorById(@PathVariable("id") Integer id) {
        LambdaQueryWrapper<SupervisorSecondLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SupervisorSecondLvDiscipline::getSecondLvDisciplineId, id);

        List<SupervisorSecondLvDiscipline> connections = supervisorSecondLvDisciplineService.list(wrapper);

        if (connections.isEmpty()) {
            return Collections.emptyList(); // 返回空列表表示没有对应的supervisor
        }

        List<String> supervisorIds = connections.stream()
                .map(SupervisorSecondLvDiscipline::getSupervisorId)
                .collect(Collectors.toList());

        LambdaQueryWrapper<Supervisor> supervisorWrapper = new LambdaQueryWrapper<>();
        supervisorWrapper.in(Supervisor::getId, supervisorIds);

        return supervisorService.list(supervisorWrapper);
    }

    //查询接口
    @GetMapping("/list")
    public Map<String, Object> getSecondLvDisciplineList(
            @RequestParam(value = "secondLvDisciplineNo", required = false) String secondLvDisciplineNo,
            @RequestParam(value = "secondLvDisciplineName", required = false) String secondLvDisciplineName,
            @RequestParam(value = "belongFirstLvDisciplineNo", required = false) String belongFirstLvDisciplineNo,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<SecondLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(StringUtils.hasLength(secondLvDisciplineNo), SecondLvDiscipline::getSecondLvDisciplineNo, secondLvDisciplineNo)
                .eq(StringUtils.hasLength(secondLvDisciplineName), SecondLvDiscipline::getSecondLvDisciplineName, secondLvDisciplineName)
                .eq(StringUtils.hasLength(belongFirstLvDisciplineNo), SecondLvDiscipline::getBelongFirstLvDisciplineNo, belongFirstLvDisciplineNo);

        // 执行查询
        IPage<SecondLvDiscipline> page = new Page<>(pageNo, pageSize);
        IPage<SecondLvDiscipline> result = secondLvDisciplineService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }


}
