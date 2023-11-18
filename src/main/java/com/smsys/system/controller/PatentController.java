package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Patent;
import com.smsys.system.service.IPatentService;
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
@RequestMapping("/system/patent")
public class PatentController {
    @Autowired
    private IPatentService patentService;
    @GetMapping("/list")
    public Map<String, Object> getPatentList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "patentName", required = false) String patentName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "firstInventor", required = false) String firstInventor,
            @RequestParam(value = "firstInventorAffiliation", required = false) String firstInventorAffiliation,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "patentNumber", required = false) String patentNumber,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Patent> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Patent::getResultId, resultId)
                .eq(StringUtils.hasLength(patentName), Patent::getPatentName, patentName)
                .eq(StringUtils.hasLength(supervisorNo), Patent::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(firstInventor), Patent::getFirstInventor, firstInventor)
                .eq(StringUtils.hasLength(firstInventorAffiliation), Patent::getFirstInventorAffiliation, firstInventorAffiliation)
                .eq(StringUtils.hasLength(type), Patent::getType, type)
                .eq(StringUtils.hasLength(patentNumber), Patent::getPatentNumber, patentNumber)
                .eq(StringUtils.hasLength(rank), Patent::getRank, rank)
                .eq(status != null, Patent::getStatus, status)
                .eq(isDeleted != null, Patent::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Patent> page = new Page<>(pageNo, pageSize);
        IPage<Patent> result = patentService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
