package com.smsys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.system.entity.Publication;
import com.smsys.system.service.IPublicationService;
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
@RequestMapping("/system/publication")
public class PublicationController {
    @Autowired
    private IPublicationService publicationService;
    @GetMapping("/list")
    public Map<String, Object> getPublicationList(
            @RequestParam(value = "resultId", required = false) Integer resultId,
            @RequestParam(value = "publicationName", required = false) String publicationName,
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "publisher", required = false) String publisher,
            @RequestParam(value = "schoolAttribution", required = false) String schoolAttribution,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "publicationDate", required = false) String publicationDate,
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "rank", required = false) String rank,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "isDeleted", required = false) Byte isDeleted,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Publication> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(resultId != null, Publication::getResultId, resultId)
                .eq(StringUtils.hasLength(publicationName), Publication::getPublicationName, publicationName)
                .eq(StringUtils.hasLength(supervisorNo), Publication::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(author), Publication::getAuthor, author)
                .eq(StringUtils.hasLength(publisher), Publication::getPublisher, publisher)
                .eq(StringUtils.hasLength(schoolAttribution), Publication::getSchoolAttribution, schoolAttribution)
                .eq(StringUtils.hasLength(category), Publication::getCategory, category)
                .eq(StringUtils.hasLength(publicationDate), Publication::getPublicationDate, publicationDate)
                .eq(StringUtils.hasLength(isbn), Publication::getIsbn, isbn)
                .eq(StringUtils.hasLength(rank), Publication::getRank, rank)
                .eq(status != null, Publication::getStatus, status)
                .eq(isDeleted != null, Publication::getIsDeleted, isDeleted);

        // 执行查询
        IPage<Publication> page = new Page<>(pageNo, pageSize);
        IPage<Publication> result = publicationService.page(page, wrapper);

        // 返回查询结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", result.getTotal());
        resultMap.put("records", result.getRecords());

        return resultMap;
    }

}
