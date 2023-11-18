package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Paper;
import com.smsys.system.service.IPaperService;
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
@RequestMapping("/system/paper")
public class PaperController {
    @Autowired
    private IPaperService paperService;
    @GetMapping("/list")
    public Map<String, Object> getPaperList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "paperName", required = false) String paperName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "firstAuthor", required = false) String firstAuthor,
            @RequestParam(value = "firstAuthorAffiliation", required = false) String firstAuthorAffiliation,
            @RequestParam(value = "correspondingAuthor", required = false) String correspondingAuthor,
            @RequestParam(value = "source", required = false) String source,
            @RequestParam(value = "publicationDate", required = false) String publicationDate,
            @RequestParam(value = "grade", required = false) String grade,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Paper::getResultId, resultId)
                .eq(StringUtils.hasLength(paperName), Paper::getPaperName, paperName)
                .eq(StringUtils.hasLength(supervisorNo), Paper::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(firstAuthor), Paper::getFirstAuthor, firstAuthor)
                .eq(StringUtils.hasLength(firstAuthorAffiliation), Paper::getFirstAuthorAffiliation, firstAuthorAffiliation)
                .eq(StringUtils.hasLength(correspondingAuthor), Paper::getCorrespondingAuthor, correspondingAuthor)
                .eq(StringUtils.hasLength(source), Paper::getSource, source)
                .eq(StringUtils.hasLength(publicationDate), Paper::getPublicationDate, publicationDate)
                .eq(StringUtils.hasLength(grade), Paper::getGrade, grade)
                .eq(StringUtils.hasLength(rank), Paper::getRank, rank)
                .eq(status != null, Paper::getStatus, status)
                .eq(isDeleted != null, Paper::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Paper> page = new Page<>(pageNo, pageSize);
        IPage<Paper> result = paperService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
