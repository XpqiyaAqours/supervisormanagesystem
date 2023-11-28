package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Vertical;
import com.smsys.system.service.IVerticalService;
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
@RequestMapping("/system/vertical")
public class VerticalController {
    @Autowired
    private IVerticalService verticalService;
    @GetMapping("/list")
    public Map<String, Object> getVerticalList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "projectName", required = false) String projectName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "funding", required = false) String funding,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Vertical> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Vertical::getResultId, resultId)
                .eq(StringUtils.hasLength(projectName), Vertical::getProjectName, projectName)
                .eq(StringUtils.hasLength(supervisorNo), Vertical::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(funding), Vertical::getFunding, funding)
                .eq(StringUtils.hasLength(startDate), Vertical::getStartDate, startDate)
                .eq(StringUtils.hasLength(endDate), Vertical::getEndDate, endDate)
                .eq(StringUtils.hasLength(level), Vertical::getLevel, level)
                .eq(StringUtils.hasLength(subject), Vertical::getSubject, subject)
                .eq(StringUtils.hasLength(rank), Vertical::getRank, rank)
                .eq(status != null, Vertical::getStatus, status)
                .eq(isDeleted != null, Vertical::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Vertical> page = new Page<>(pageNo, pageSize);
        IPage<Vertical> result = verticalService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
