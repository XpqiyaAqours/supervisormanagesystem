package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.SupervisorStudent;
import com.smsys.system.service.ISupervisorStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/system/supervisorstudent")
public class SupervisorStudentController {
    @Autowired
    private ISupervisorStudentService supervisorStudentService;
    @PostMapping("/createconnection")
    public String createConnection(
            @RequestParam("supervisorId") String supervisorId,
            @RequestParam("studentId") String studentId) {

        SupervisorStudent connection = new SupervisorStudent();
        connection.setSupervisorId(supervisorId);
        connection.setStudentId(studentId);

        boolean success = supervisorStudentService.save(connection);

        return success ? "Connection created successfully" : "Failed to create connection";
    }
    @GetMapping("/connectionslist")
    public Map<String, Object> listConnections(
            @RequestParam(value = "supervisorId", required = false) String supervisorId,
            @RequestParam(value = "studentId", required = false) String studentId,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<SupervisorStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(StringUtils.hasLength(supervisorId), SupervisorStudent::getSupervisorId, supervisorId)
                .eq(StringUtils.hasLength(studentId), SupervisorStudent::getStudentId, studentId);

        IPage<SupervisorStudent> page = new Page<>(pageNo, pageSize);
        IPage<SupervisorStudent> result = supervisorStudentService.page(page, wrapper);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }
    @DeleteMapping("/deleteconnection")
    public String deleteConnection(
            @RequestParam("supervisorId") String supervisorId,
            @RequestParam("studentId") String studentId) {

        LambdaQueryWrapper<SupervisorStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(SupervisorStudent::getSupervisorId, supervisorId)
                .eq(SupervisorStudent::getStudentId, studentId);

        boolean success = supervisorStudentService.remove(wrapper);

        return success ? "Connection deleted successfully" : "Failed to delete connection";
    }

}
