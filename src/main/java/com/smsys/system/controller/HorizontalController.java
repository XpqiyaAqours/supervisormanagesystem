package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Horizontal;
import com.smsys.system.service.IHorizontalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
@RequestMapping("/system/horizontal")
public class HorizontalController {
    @Autowired
    private IHorizontalService horizontalService;
    @GetMapping("/list")
    public Map<String, Object> getHorizontalList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "projectName", required = false) String projectName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "income", required = false) String income,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Horizontal> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Horizontal::getResultId, resultId)
                .eq(StringUtils.hasLength(projectName), Horizontal::getProjectName, projectName)
                .eq(StringUtils.hasLength(supervisorNo), Horizontal::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(income), Horizontal::getIncome, income)
                .eq(StringUtils.hasLength(startDate), Horizontal::getStartDate, startDate)
                .eq(StringUtils.hasLength(endDate), Horizontal::getEndDate, endDate)
                .eq(StringUtils.hasLength(subject), Horizontal::getSubject, subject)
                .eq(StringUtils.hasLength(rank), Horizontal::getRank, rank)
                .eq(status != null, Horizontal::getStatus, status)
                .eq(isDeleted != null, Horizontal::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Horizontal> page = new Page<>(pageNo, pageSize);
        IPage<Horizontal> result = horizontalService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
