package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Resultlist;
import com.smsys.system.service.IResultlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlx
 * @since 2023-11-01
 */
@RestController
@RequestMapping("/system/resultlist")
public class ResultlistController {
    @Autowired
    private IResultlistService resultListService;
    @GetMapping("/list")
    public Map<String, Object> getResultList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "supervisorId", required = false) Integer supervisorId,
            @RequestParam(value = "resultType", required = false) Integer resultType,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Resultlist> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Resultlist::getResultId, resultId)
                .eq(supervisorId != null, Resultlist::getSupervisorId, supervisorId)
                .eq(resultType != null, Resultlist::getResultType, resultType);

        // 执行查询
        IPage<Resultlist> page = new Page<>(pageNo, pageSize);
        IPage<Resultlist> result = resultListService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
