package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.SupervisorSecondLvDiscipline;
import com.smsys.system.entity.SupervisorStudent;
import com.smsys.system.service.ISupervisorSecondLvDisciplineService;
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
@RequestMapping("/system/supervisorsecondlvdiscipline")
public class SupervisorSecondLvDisciplineController {
    @Autowired
    private ISupervisorSecondLvDisciplineService supervisorSecondLvDisciplineService;

    // 建立连接
    @PostMapping("/connect")
    public String connectSupervisorAndSecondLvDiscipline(
            @RequestParam("supervisorId") String supervisorId,
            @RequestParam("secondLvDisciplineId") String secondLvDisciplineId) {
        SupervisorSecondLvDiscipline connection = new SupervisorSecondLvDiscipline();
        connection.setSupervisorId(supervisorId);
        connection.setSecondLvDisciplineId(secondLvDisciplineId);
        supervisorSecondLvDisciplineService.save(connection);
        return "Connection established successfully";
    }

    // 查询连接
    @GetMapping("/connectionslist")
    public Map<String, Object> listConnections(
            @RequestParam(value = "supervisorId", required = false) String supervisorId,
            @RequestParam(value = "secondLvDisciplineId", required = false) String secondLvDisciplineId,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<SupervisorSecondLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(StringUtils.hasLength(supervisorId), SupervisorSecondLvDiscipline::getSupervisorId, supervisorId)
                .eq(StringUtils.hasLength(secondLvDisciplineId), SupervisorSecondLvDiscipline::getSecondLvDisciplineId, secondLvDisciplineId);

        IPage<SupervisorSecondLvDiscipline> page = new Page<>(pageNo, pageSize);
        IPage<SupervisorSecondLvDiscipline> result = supervisorSecondLvDisciplineService.page(page, wrapper);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

    // 删除连接
    @DeleteMapping("/disconnect")
    public String disconnectSupervisorAndSecondLvDiscipline(
            @RequestParam("supervisorId") String supervisorId,
            @RequestParam("secondLvDisciplineId") String secondLvDisciplineId) {
        LambdaQueryWrapper<SupervisorSecondLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(SupervisorSecondLvDiscipline::getSupervisorId, supervisorId)
                .eq(SupervisorSecondLvDiscipline::getSecondLvDisciplineId, secondLvDisciplineId);
        supervisorSecondLvDisciplineService.remove(wrapper);
        return "Connection removed successfully";
    }
}
