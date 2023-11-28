package com.smsys.system.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.dto.SupervisorDTO;
import com.smsys.dto.SupervisorInfoDTO;
import com.smsys.system.entity.*;
import com.smsys.system.mapper.SupervisorMapper;
import com.smsys.system.mapper.UserMapper;
import com.smsys.system.service.*;
import com.smsys.vo.ResultData;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
@RequestMapping("/system/supervisor")
public class SupervisorController {
    @Autowired
    ISupervisorService supervisorService;
    @Autowired
    IResultlistService resultlistService;
    //新增导师信息
    @PostMapping
    public String addSupervisor(@RequestBody Supervisor supervisor){
        supervisor.setId(null);
        supervisorService.save(supervisor);
        String result = "新增用户成功";
        return result;

    }
    //修改信息
    @PutMapping
    public String updateSupervisor(@RequestBody Supervisor supervisor){
        supervisorService.updateById(supervisor);
        String result = "修改信息成功";
        return result;
    }
    //按ID查找导师信息
    @GetMapping("/{id}")
    public Supervisor getSupervisorById(@PathVariable("id")Integer id){
        Supervisor supervisor  = supervisorService.getById(id);
        return supervisor;
    }

    //导师查询功能
    @GetMapping("/list")
    public Map<String, Object> getSupervisorList(
            @RequestParam(value = "supervisorNo", required = false) String supervisorNo,
            @RequestParam(value = "supervisorName", required = false) String supervisorName,
            @RequestParam(value = "sex", required = false) Byte sex,
            @RequestParam(value = "job", required = false) String job,
            @RequestParam(value = "educationalBackground", required = false) String educationalBackground,
            @RequestParam(value = "degree", required = false) String degree,
            @RequestParam(value = "birthDate", required = false) String birthDate,
            @RequestParam(value = "researchAreas", required = false) String researchAreas,
            @RequestParam(value = "offCampus", required = false) Byte offCampus,
            @RequestParam(value = "honoraryTitles", required = false) String honoraryTitles,
            @RequestParam(value = "belongCollege", required = false) String belongCollege,
            @RequestParam(value = "belongSecondLvDisciplineNo", required = false) String belongSecondLvDisciplineNo,
            @RequestParam(value = "researchProjectsCheck", required = false) Byte researchProjectsCheck,
            @RequestParam(value = "researchResultsCheck", required = false) Byte researchResultsCheck,
            @RequestParam(value = "graduateSchoolCheck", required = false) Byte graduateSchoolCheck,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "pageNo") long pageNo,
            @RequestParam(value = "pageSize") long pageSize) {

        LambdaQueryWrapper<Supervisor> wrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        wrapper
                .eq(StringUtils.hasLength(supervisorNo), Supervisor::getSupervisorNo, supervisorNo)
                .eq(StringUtils.hasLength(supervisorName), Supervisor::getSupervisorName, supervisorName)
                .eq(sex != null, Supervisor::getSex, sex)
                .eq(StringUtils.hasLength(job), Supervisor::getJob, job)
                .eq(StringUtils.hasLength(educationalBackground), Supervisor::getEducationalBackground, educationalBackground)
                .eq(StringUtils.hasLength(degree), Supervisor::getDegree, degree)
                .eq(StringUtils.hasLength(birthDate), Supervisor::getBirthDate, birthDate)
                .eq(StringUtils.hasLength(researchAreas), Supervisor::getResearchAreas, researchAreas)
                .eq(offCampus != null, Supervisor::getOffCampus, offCampus)
                .eq(StringUtils.hasLength(honoraryTitles), Supervisor::getHonoraryTitles, honoraryTitles)
                .eq(StringUtils.hasLength(belongCollege), Supervisor::getBelongCollege, belongCollege)
                .eq(StringUtils.hasLength(belongSecondLvDisciplineNo), Supervisor::getBelongSecondLvDisciplineNo, belongSecondLvDisciplineNo)
                .eq(researchProjectsCheck != null, Supervisor::getResearchProjectsCheck, researchProjectsCheck)
                .eq(researchResultsCheck != null, Supervisor::getResearchResultsCheck, researchResultsCheck)
                .eq(graduateSchoolCheck != null, Supervisor::getGraduateSchoolCheck, graduateSchoolCheck)
                .eq(status != null, Supervisor::getStatus, status);

        Page<Supervisor> Page = new Page<>(pageNo, pageSize);
        supervisorService.page(Page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", Page.getTotal());
        data.put("rows", Page.getRecords());
        return data;
    }
    //审核状态修改
    @PutMapping("/status")
    public String updatePassCheck(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "status") Byte status){
        Supervisor supervisor = new Supervisor();
        supervisor.setId(id);
        supervisor.setStatus(status);
        Boolean rowsAffected = supervisorService.updateById(supervisor);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    //新增导师项目接口
    @PostMapping("/verticalresult")
    public String addVerticalResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Vertical vertical) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(1);
        resultlistService.save(resultlist);
        vertical.setId(null);
        vertical.setResultId(resultlist.getResultId());
        verticalService.save(vertical);
        String result = "新增项目成功";
        return result;
    }
    @PostMapping("/horizontalresult")
    public String addHorizontalResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Horizontal horizontal) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(2);
        resultlistService.save(resultlist);
        horizontal.setId(null);
        horizontal.setResultId(resultlist.getResultId());
        horizontalService.save(horizontal);
        String result = "新增项目成功";
        return result;
    }
    @PostMapping("/paperresult")
    public String addPaperResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Paper paper) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(3);
        resultlistService.save(resultlist);
        paper.setId(null);
        paper.setResultId(resultlist.getResultId());
        paperService.save(paper);
        String result = "新增项目成功";
        return result;
    }
    @PostMapping("/awardresult")
    public String addAwardResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Award award) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(4);
        resultlistService.save(resultlist);
        award.setId(null);
        award.setResultId(resultlist.getResultId());
        awardService.save(award);
        String result = "新增项目成功";
        return result;
    }
    @PostMapping("/publicationresult")
    public String addPublicationResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Publication publication) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(5);
        resultlistService.save(resultlist);
        publication.setId(null);
        publication.setResultId(resultlist.getResultId());
        publicationService.save(publication);
        String result = "新增项目成功";
        return result;
    }
    @PostMapping("/patentresult")
    public String addPatentResult(@RequestParam(value = "supervisorId") Integer id,
                            @RequestBody Patent patent) {
        Resultlist resultlist = new Resultlist();
        resultlist.setSupervisorId(id);
        resultlist.setResultType(6);
        resultlistService.save(resultlist);
        patent.setId(null);
        patent.setResultId(resultlist.getResultId());
        patentService.save(patent);
        String result = "新增项目成功";
        return result;
    }
    //修改项目接口
    @PutMapping("/verticalresult")
    public String updataVerticalResult(@RequestBody Vertical vertical) {
        Boolean rowsAffected = verticalService.updateById(vertical);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    @PutMapping("/horizontalresult")
    public String updataHorizontalResult(@RequestBody Horizontal horizontal) {
        Boolean rowsAffected = horizontalService.updateById(horizontal);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    @PutMapping("/paperresult")
    public String updataPaperResult(@RequestBody Paper paper) {
        Boolean rowsAffected = paperService.updateById(paper);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    @PutMapping("/awardresult")
    public String updataAwardResult(@RequestBody Award award) {
        Boolean rowsAffected = awardService.updateById(award);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    @PutMapping("/publicationresult")
    public String updataPublicationResult(@RequestBody Publication publication) {
        Boolean rowsAffected = publicationService.updateById(publication);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }
    @PutMapping("/patentresult")
    public String updataPatentResult(@RequestBody Patent patent) {
        Boolean rowsAffected = patentService.updateById(patent);
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }
    }




    //按导师ID查找导师项目
    @Autowired
    IVerticalService verticalService;
    @Autowired
    IHorizontalService horizontalService;
    @Autowired
    IPaperService paperService;
    @Autowired
    IAwardService awardService;
    @Autowired
    IPublicationService publicationService;
    @Autowired
    IPatentService patentService;
    @GetMapping("/resultlist/{id}")
    public List getResultBySupervisorId(@PathVariable("id")Integer id){
        List<Object> result = new ArrayList<>();
        List<Resultlist> resultlist = resultlistService.getResultListBySupervisorId(id);
        System.out.println(resultlist);
        for (int i = 0; i < resultlist.size(); i++) {
            switch(resultlist.get(i).getResultType()){
                case 1:
                    Object Result1 = verticalService.getByResultId(resultlist.get(i).getResultId());
                    if (Result1 != null) {
                        result.add(Result1);
                    }
                    break;
                case 2:
                    Object Result2 = horizontalService.getByResultId(resultlist.get(i).getResultId());
                    if (Result2 != null) {
                        result.add(Result2);
                    }
                    break;
                case 3:
                    Object Result3 = paperService.getByResultId(resultlist.get(i).getResultId());
                    if (Result3 != null) {
                        result.add(Result3);
                    }
                    break;
                case 4:
                    Object Result4 = awardService.getByResultId(resultlist.get(i).getResultId());
                    if (Result4 != null) {
                        result.add(Result4);
                    }
                    break;
                case 5:
                    Object Result5 = publicationService.getByResultId(resultlist.get(i).getResultId());
                    if (Result5 != null) {
                        result.add(Result5);
                    }
                    break;
                case 6:
                    Object Result6 = patentService.getByResultId(resultlist.get(i).getResultId());
                    if (Result6 != null) {
                        result.add(Result6);
                    }
                    break;
            }
        }
        return result;
    }


    //按ID查找项目
    @GetMapping("/result/{id}")
    public Object getResultByResultId(@PathVariable("id")Integer id){
        Object Result = new Object();
        try {
            switch (resultlistService.getById(id).getResultType()) {
                case 1:
                    Result = verticalService.getByResultId(id);
                    break;
                case 2:
                    Result = horizontalService.getByResultId(id);
                    break;
                case 3:
                    Result = paperService.getByResultId(id);
                    break;
                case 4:
                    Result = awardService.getByResultId(id);
                    break;
                case 5:
                    Result = publicationService.getByResultId(id);
                    break;
                case 6:
                    Result = patentService.getByResultId(id);
                    break;
            }
        }catch (NullPointerException E){
            throw new RuntimeException("未找到该项目");
        }
        if(Result != null){
            return Result;
        }
        throw new RuntimeException("未找到该项目");
    }

    //根据项目id修改项目审核状态（注意：项目id指result_id，而非id）
    @PutMapping("/resultstatus")
    public String setResultStatus(@RequestParam(value = "resultId") Integer id,
                                  @RequestParam(value = "status") Byte status){
        Boolean rowsAffected = false;
        switch (resultlistService.getById(id).getResultType()) {
            case 1:
                Vertical vertical = new Vertical();
                vertical.setId(verticalService.getByResultId(id).getId());
                vertical.setStatus(status);
                rowsAffected = verticalService.updateById(vertical);
                break;
            case 2:
                Horizontal horizontal = new Horizontal();
                horizontal.setId(horizontalService.getByResultId(id).getId());
                horizontal.setStatus(status);
                rowsAffected = horizontalService.updateById(horizontal);
                break;
            case 3:
                Paper paper= new Paper();
                paper.setId(paperService.getByResultId(id).getId());
                paper.setStatus(status);
                rowsAffected = paperService.updateById(paper);
                break;
            case 4:
                Award award= new Award();
                award.setId(awardService.getByResultId(id).getId());
                award.setStatus(status);
                rowsAffected = awardService.updateById(award);
                break;
            case 5:
                Publication publication= new Publication();
                publication.setId(publicationService.getByResultId(id).getId());
                publication.setStatus(status);
                rowsAffected = publicationService.updateById(publication);
                break;
            case 6:
                Patent patent= new Patent();
                patent.setId(patentService.getByResultId(id).getId());
                patent.setStatus(status);
                rowsAffected = patentService.updateById(patent);
                break;
        }
        if (rowsAffected) {
            return "更新成功";
        } else {
            return "更新失败"; }

    }
    //删除项目
    @DeleteMapping("/result/{id}")
    public String deleteResult(@PathVariable("id")Integer id){
        switch (resultlistService.getById(id).getResultType()) {
            case 1:
                verticalService.removeById(verticalService.getByResultId(id).getId());
                break;
            case 2:
                horizontalService.removeById(horizontalService.getByResultId(id).getId());
                break;
            case 3:
                paperService.removeById(paperService.getByResultId(id).getId());
                break;
            case 4:
                awardService.removeById(awardService.getByResultId(id).getId());
                break;
            case 5:
                publicationService.removeById(publicationService.getByResultId(id).getId());
                break;
            case 6:
                patentService.removeById(patentService.getByResultId(id).getId());
                break;
        }
        resultlistService.removeById(id);
        return "项目删除成功";
    }
    //excel导出个人表:
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportSupervisorsToExcel(@RequestParam("id") List<Integer> id) {
        try {
            List<Supervisor> supervisorList = supervisorService.listByIds(id);

            byte[] excelBytes = exportToExcel(supervisorList);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "supervisors_data.xls");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    private byte[] exportToExcel(List<Supervisor> supervisorList) throws IOException {

        // 设置导出参数，具体可根据需要进行调整
        ExportParams exportParams = new ExportParams();
        exportParams.setTitle("Supervisors Data");
        exportParams.setSheetName("Sheet1");
        exportParams.setType(ExcelType.XSSF);

        // 写入数据
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Supervisor.class, supervisorList);

        // 将Excel内容写入字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
    //excel导入个人表:
    @PostMapping("/import")
    public ResultData<String> importExcel(@RequestParam("file") MultipartFile file) {
        Boolean rowsAffected = false;
        try {
            ImportParams params = new ImportParams();
            //标题和表头
            params.setStartRows(0);
            params.setHeadRows(1);
            params.setTitleRows(1);
            List<SupervisorInfoDTO> supervisorInfoDTOList = ExcelImportUtil.importExcel(file.getInputStream(), SupervisorInfoDTO.class, params);
            System.out.println(supervisorInfoDTOList);
            // 然后遍历 supervisorDTOList，将数据映射到实际的数据表实体类 Supervisor 中，并更新数据库
            for (SupervisorInfoDTO dto : supervisorInfoDTOList) {
                Supervisor supervisor = new Supervisor();
                System.out.println(supervisor);
                supervisor.setId(dto.getId());
                supervisor.setSupervisorNo(dto.getSupervisorNo());
                supervisor.setSupervisorName(dto.getSupervisorName());
                supervisor.setSex((byte)dto.getSex());
                supervisor.setJob(dto.getJob());
                supervisor.setEducationalBackground(dto.getEducationalBackground());
                supervisor.setDegree(dto.getDegree());
                supervisor.setBirthDate(dto.getBirthDate());
                supervisor.setResearchAreas(dto.getResearchAreas());
                supervisor.setOffCampus((byte)dto.getOffCampus());
                supervisor.setHonoraryTitles(dto.getHonoraryTitles());
                supervisor.setBelongCollege(dto.getBelongCollege());
                System.out.println(supervisor);
                rowsAffected = supervisorService.updateById(supervisor);
            }
            if (rowsAffected) {
                return ResultData.success("导入成功");
            }
            return ResultData.fail(500, "导入失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(500, "导入失败");
        }
    }
    //使用easypoi的replace注释配合数据类型强转实现了魔法值的转换，所以以下方法废弃了
    private Supervisor mapDTOToEntity(SupervisorInfoDTO dto) {
        Supervisor supervisor = new Supervisor();
        supervisor.setId(dto.getId());
        supervisor.setSupervisorNo(dto.getSupervisorNo());
        supervisor.setSupervisorName(dto.getSupervisorName());

        // 将字符串类型的性别转换为 Byte
        //supervisor.setSex("男".equals(dto.getSex()) ? (byte) 1 : (byte) 0);
        //supervisor.setOffCampus("是".equals(dto.getOffCampus()) ? (byte) 1 : (byte) 0);


        return supervisor;
    }

    //打印各种审核状态下的导师名单
    @GetMapping("/exportlist")
    public ResponseEntity<byte[]> exportSupervisorsListToExcel(@RequestParam("status") Byte status) {
        try {
            List<Supervisor> supervisorList = supervisorService.list();
            List<Supervisor> filteredSupervisorList = new ArrayList<>();

            for (Supervisor supervisor : supervisorList) {
                if (supervisor.getStatus() == status) {
                    filteredSupervisorList.add(supervisor);
                }
            }

            byte[] excelBytes = exportListToExcel(filteredSupervisorList);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "supervisors_data.xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    private byte[] exportListToExcel(List<Supervisor> supervisorList) throws IOException {

        // 设置导出参数，具体可根据需要进行调整
        ExportParams exportParams = new ExportParams();
        exportParams.setTitle("Supervisors Data");
        exportParams.setSheetName("Sheet1");
        exportParams.setType(ExcelType.XSSF);

        // 写入数据
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Supervisor.class, supervisorList);

        // 将Excel内容写入字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    //查询对应二级学科点信息
    @Autowired
    private ISupervisorSecondLvDisciplineService supervisorSecondLvDisciplineService;
    @Autowired
    private ISecondLvDisciplineService secondLvDisciplineService;
    @GetMapping("/secondlvdisciplines/{supervisorId}")
    public List<SecondLvDiscipline> getSecondLvDisciplinesBySupervisorId(@PathVariable String supervisorId) {
        LambdaQueryWrapper<SupervisorSecondLvDiscipline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SupervisorSecondLvDiscipline::getSupervisorId, supervisorId);

        List<SupervisorSecondLvDiscipline> connections = supervisorSecondLvDisciplineService.list(wrapper);

        if (connections.isEmpty()) {
            return Collections.emptyList(); // 返回空列表表示没有对应的SecondLvDiscipline
        }

        List<String> secondLvDisciplineIds = connections.stream()
                .map(SupervisorSecondLvDiscipline::getSecondLvDisciplineId)
                .collect(Collectors.toList());

        LambdaQueryWrapper<SecondLvDiscipline> secondLvDisciplineWrapper = new LambdaQueryWrapper<>();
        secondLvDisciplineWrapper.in(SecondLvDiscipline::getId, secondLvDisciplineIds);

        return secondLvDisciplineService.list(secondLvDisciplineWrapper);
    }
}

