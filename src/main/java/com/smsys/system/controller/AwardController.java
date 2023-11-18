package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Award;
import com.smsys.system.service.IAwardService;
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
@RequestMapping("/system/award")
public class AwardController {
    @Autowired
    private IAwardService awardService;
    @GetMapping("/list")
    public Map<String, Object> getAwardList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "awardName", required = false) String awardName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Award> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Award::getResultId, resultId)
                .eq(StringUtils.hasLength(awardName), Award::getAwardName, awardName)
                .eq(StringUtils.hasLength(supervisorNo), Award::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(type), Award::getType, type)
                .eq(StringUtils.hasLength(date), Award::getDate, date)
                .eq(StringUtils.hasLength(rank), Award::getRank, rank)
                .eq(status != null, Award::getStatus, status)
                .eq(isDeleted != null, Award::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Award> page = new Page<>(pageNo, pageSize);
        IPage<Award> result = awardService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
