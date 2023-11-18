package com.smsys.system.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smsys.dto.*;
import com.smsys.system.entity.*;
import com.smsys.system.mapper.CollegeMapper;
import com.smsys.system.mapper.UserMapper;
import com.smsys.system.model.*;
import com.smsys.system.service.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.ByteArrayOutputStream;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlx
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICollegeService collegeService;
    @Autowired
    private IFirstLvDisciplineService firstLvDisciplineService;
    @Autowired
    private ISecondLvDisciplineService secondLvDisciplineService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ISupervisorService supervisorService;

    @GetMapping("/all")
    public List<User> getalluser() {
        List<User> list = userService.list();
        return list;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> data = userService.login(user, session, request, response);
        if (data != null) {
            return data;
        }
        throw new RuntimeException("用户名或密码错误");
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        //根据token获取用户信息
        Map<String, Object> data = userService.getUserInfo(req, resp);
        if (data != null) {
            return data;
        }
        throw new RuntimeException("用户无效");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        userService.logout(session, request, response);
        String data = "登出成功";
        return data;
    }

    @GetMapping("/list")
    //用户查询功能
    public Map<String, Object> getUserList(@RequestParam(value = "username", required = false) String username,
                                           @RequestParam(value = "role", required = false) Integer role,
                                           @RequestParam(value = "pageNo") long pageNo,
                                           @RequestParam(value = "pageSize") long pageSize) {
        System.out.println(username);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username), User::getUsername, username);
        wrapper.eq(role != null, User::getRole, role);
        wrapper.orderByDesc(User::getId);

        Page<User> Page = new Page<>(pageNo, pageSize);
        userService.page(Page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", Page.getTotal());
        data.put("rows", Page.getRecords());
        System.out.println(data);
        return data;

    }

    //新增用户
    @PostMapping
    public String addUser(@RequestBody User user) {
        user.setId(null);
        userService.save(user);
        String result = "新增用户成功";
        return result;

    }

    //修改用户
    @PutMapping
    public String updateUser(@RequestBody User user) {
        userService.updateById(user);
        String result = "修改用户成功";
        return result;
    }

    //按ID查找用户
    //用于在修改用户的界面展示原用户信息
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return user;
    }

    //删除用户
    //@DeleteMapping("/{id}")
    //public String deleteUserById(@PathVariable("id")Integer id){
    //    userService.removeById(id);
    //    return "用户删除成功";
    //}
    //获取用户角色
    @GetMapping("/getuserrole/{id}")
    public Integer getUserRole(@PathVariable("id") Integer id) {
        Integer role = userService.getById(id).getRole();
        return role;
    }

    //根据用户id获取用户详情信息
    @GetMapping("/getuserdetail/{id}")
    public String getUserDetail(@PathVariable("id") Integer id) {
        Integer role = userService.getById(id).getRole();
        String data = null;
        try {
            switch (role) {
                case 0:
                    data = "This user is admin.";
                    break;
                case 1:
                case 2:
                    data = "This user belong to graduate school.";
                    break;
                case 3:
                    CollegeDetail collegeDetail = collegeService.getCollegeDetail(id);
                    data = collegeDetail.toString();
                    break;
                case 4:
                    FirstLvDisciplineDetail firstLvDisciplineDetail = firstLvDisciplineService.getFirstLvDisciplineDetail(id);
                    data = firstLvDisciplineDetail.toString();
                    break;
                case 5:
                    SecondLvDisciplineDetail secondLvDisciplineDetail = secondLvDisciplineService.getSecondLvDisciplineDetail(id);
                    data = secondLvDisciplineDetail.toString();
                    break;
                case 6:
                    SupervisorDetail supervisorDetail = supervisorService.getSupervisorDetail(id);
                    data = supervisorDetail.toString();
                    break;
                case 7:
                    StudentDetail studentDetail = studentService.getStudentDetail(id);
                    data = studentDetail.toString();
                    break;
                default:
                    throw new RuntimeException("用户权限错误");
            }
        }catch (NullPointerException E){
            throw new RuntimeException("未找到用户详情信息");
        }

        if (data != null) {
            return data;
        }
        throw new RuntimeException("未找到用户详情信息");
    }

    //删除用户
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        Integer role = userService.getById(id).getRole();
        try {
        switch (role) {
            case 0:
            case 1:
            case 2:
                userService.removeById(id);
                return "用户删除成功";
            case 3:
                CollegeDetail collegeDetail = collegeService.getCollegeDetail(id);
                collegeService.removeById(collegeDetail.getId());
                userService.removeById(id);
                return "二级学院用户删除成功";
            case 4:
                FirstLvDisciplineDetail firstLvDisciplineDetail = firstLvDisciplineService.getFirstLvDisciplineDetail(id);
                firstLvDisciplineService.removeById(firstLvDisciplineDetail.getId());
                userService.removeById(id);
                return "一级学科点用户删除成功";
            case 5:
                SecondLvDisciplineDetail secondLvDisciplineDetail = secondLvDisciplineService.getSecondLvDisciplineDetail(id);
                secondLvDisciplineService.removeById(secondLvDisciplineDetail.getId());
                userService.removeById(id);
                return "二级学科点用户删除成功";
            case 6:
                SupervisorDetail supervisorDetail = supervisorService.getSupervisorDetail(id);
                supervisorService.removeById(supervisorDetail.getId());
                userService.removeById(id);
                return "导师用户删除成功";
            case 7:
                StudentDetail studentDetail = studentService.getStudentDetail(id);
                studentService.removeById(studentDetail.getId());
                userService.removeById(id);
                return "研究生用户删除成功";
            default:
                throw new RuntimeException("未找到正确用户");
        }
        }catch (NullPointerException E){
            throw new RuntimeException("未找到正确用户");
        }
    }


    //批量上传学院用户
    @PostMapping("/uploadcollege")
    public ResponseEntity<String> handleFileCollegeUpload(@RequestParam("file") MultipartFile file) {
        try {
            List<CollegeDTO> dataList = parseCollegeExcel(file);

            for (CollegeDTO collegeDTO : dataList) {
                if (collegeDTO.getUsername() != null) {
                    // 将 username、password、role 插入到 user 表
                    User user = new User();
                    user.setUsername(collegeDTO.getUsername());
                    user.setPassword("000000");
                    user.setRole(3);

                    userService.save(user);

                    // 获取新增记录的 id
                    Integer userId = user.getId();

                    // 将剩余字段插入到对应表
                    College college = new College();
                    college.setUserId(userId);
                    college.setCollegeNo(collegeDTO.getCollegeNo());
                    college.setCollegeName(collegeDTO.getCollegeName());
                    college.setUser(collegeDTO.getUser());
                    college.setAuditor(collegeDTO.getAuditor());

                    collegeService.save(college);
                }
            }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the Excel file.");
        }
    }
        private List<CollegeDTO> parseCollegeExcel(MultipartFile file) {
            try {
                ImportParams params = new ImportParams();
                //标题和表头
                params.setStartRows(0);
                params.setHeadRows(1);
                params.setTitleRows(0);

                List<CollegeDTO> result = ExcelImportUtil.importExcel(file.getInputStream(),
                        CollegeDTO.class, params);
                System.out.println(result.toString());
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                // 处理异常，这里简单打印了异常堆栈
                return Collections.emptyList(); // 返回空列表表示解析失败
            }
    }
    //批量上传学生用户
    @PostMapping("/uploadstudent")
    public ResponseEntity<String> handleFileStudentUpload(@RequestParam("file") MultipartFile file) {
        try {
            List<StudentDTO> dataList = parseStudentExcel(file);

            for (StudentDTO studentDTO : dataList) {
                  if (studentDTO.getUsername() != null) {
                      // 将 username、password、role 插入到 user 表
                      User user = new User();
                      user.setUsername(studentDTO.getUsername());
                      user.setPassword("000000");
                      user.setRole(7);

                      userService.save(user);

                      // 获取新增记录的 id
                      Integer userId = user.getId();

                      // 将剩余字段插入到对应表
                      Student student = new Student();
                      student.setUserId(userId);
                      student.setStudentNo(studentDTO.getStudentNo());
                      student.setStudentName(studentDTO.getStudentName());
                      student.setSex((byte) studentDTO.getSex());
                      student.setBelongCollege(studentDTO.getBelongCollege());

                      studentService.save(student);
                  }
            }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the Excel file.");
        }
    }
    private List<StudentDTO> parseStudentExcel(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            //标题和表头
            params.setStartRows(0);
            params.setHeadRows(1);
            params.setTitleRows(0);

            List<StudentDTO> result = ExcelImportUtil.importExcel(file.getInputStream(),
                    StudentDTO.class, params);
            System.out.println(result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return Collections.emptyList(); // 返回空列表表示解析失败
        }
    }

    //批量上传一级学院用户
    @PostMapping("/uploadfirstLvDiscipline")
    public ResponseEntity<String> handleFileFirstLvDisciplineUpload(@RequestParam("file") MultipartFile file) {
        try {
            List<FirstLvDisciplineDTO> dataList = parseFirstLvDisciplineExcel(file);

            for (FirstLvDisciplineDTO firstLvDisciplineDTO : dataList) {
                if (firstLvDisciplineDTO.getUsername() != null) {
                    // 将 username、password、role 插入到 user 表
                    User user = new User();
                    user.setUsername(firstLvDisciplineDTO.getUsername());
                    user.setPassword("000000");
                    user.setRole(4);

                    userService.save(user);

                    // 获取新增记录的 id
                    Integer userId = user.getId();

                    // 将剩余字段插入到对应表
                    FirstLvDiscipline firstLvDiscipline = new FirstLvDiscipline();
                    firstLvDiscipline.setUserId(userId);
                    firstLvDiscipline.setFirstLvDisciplineNo(firstLvDisciplineDTO.getFirstLvDisciplineNo());
                    firstLvDiscipline.setFirstLvDisciplineName(firstLvDisciplineDTO.getFirstLvDisciplineName());

                    firstLvDisciplineService.save(firstLvDiscipline);
                }
            }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the Excel file.");
        }
    }
    private List<FirstLvDisciplineDTO> parseFirstLvDisciplineExcel(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            //标题和表头
            params.setStartRows(0);
            params.setHeadRows(1);
            params.setTitleRows(0);

            List<FirstLvDisciplineDTO> result = ExcelImportUtil.importExcel(file.getInputStream(),
                    FirstLvDisciplineDTO.class, params);
            System.out.println(result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return Collections.emptyList(); // 返回空列表表示解析失败
        }
    }


    //批量上传二级学院用户
    @PostMapping("/uploadsecondLvDiscipline")
    public ResponseEntity<String> handleFileSecondLvDisciplineUpload(@RequestParam("file") MultipartFile file) {
        try {
            List<SecondLvDisciplineDTO> dataList = parseSecondLvDisciplineExcel(file);

            for (SecondLvDisciplineDTO secondLvDisciplineDTO : dataList) {
                if (secondLvDisciplineDTO.getUsername() != null) {
                    // 将 username、password、role 插入到 user 表
                    User user = new User();
                    user.setUsername(secondLvDisciplineDTO.getUsername());
                    user.setPassword("000000");
                    user.setRole(5);

                    userService.save(user);

                    // 获取新增记录的 id
                    Integer userId = user.getId();

                    // 将剩余字段插入到对应表
                    SecondLvDiscipline secondLvDiscipline = new SecondLvDiscipline();
                    secondLvDiscipline.setUserId(userId);
                    secondLvDiscipline.setSecondLvDisciplineNo(secondLvDisciplineDTO.getSecondLvDisciplineNo());
                    secondLvDiscipline.setSecondLvDisciplineName(secondLvDisciplineDTO.getSecondLvDisciplineName());
                    secondLvDiscipline.setBelongFirstLvDisciplineNo(secondLvDisciplineDTO.getBelongFirstLvDisciplineNo());

                    secondLvDisciplineService.save(secondLvDiscipline);
                }
            }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the Excel file.");
        }
    }
    private List<SecondLvDisciplineDTO> parseSecondLvDisciplineExcel(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            //标题和表头
            params.setStartRows(0);
            params.setHeadRows(1);
            params.setTitleRows(0);

            List<SecondLvDisciplineDTO> result = ExcelImportUtil.importExcel(file.getInputStream(),
                    SecondLvDisciplineDTO.class, params);
            System.out.println(result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return Collections.emptyList(); // 返回空列表表示解析失败
        }
    }


    //批量上传导师用户
    @PostMapping("/uploadsupervisor")
    public ResponseEntity<String> handleFileSupervisorUpload(@RequestParam("file") MultipartFile file) {
        try {
            List<SupervisorDTO> dataList = parseSupervisorExcel(file);
            for (SupervisorDTO supervisorDTO : dataList) {
                if (supervisorDTO.getUsername() != null) {
                    // 将 username、password、role 插入到 user 表
                    User user = new User();
                    user.setUsername(supervisorDTO.getUsername());
                    user.setPassword("000000");
                    user.setRole(6);

                    userService.save(user);

                    // 获取新增记录的 id
                    Integer userId = user.getId();

                    // 将剩余字段插入到对应表
                    Supervisor supervisor = new Supervisor();
                    supervisor.setUserId(userId);
                    supervisor.setSupervisorNo(supervisorDTO.getSupervisorNo());
                    supervisor.setSupervisorName(supervisorDTO.getSupervisorName());

                    supervisorService.save(supervisor);
                }
            }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the Excel file.");
        }
    }
    private List<SupervisorDTO> parseSupervisorExcel(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            //标题和表头
            params.setStartRows(0);
            params.setHeadRows(1);
            params.setTitleRows(0);

            List<SupervisorDTO> result = ExcelImportUtil.importExcel(file.getInputStream(),
                    SupervisorDTO.class, params);
            System.out.println(result.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return Collections.emptyList(); // 返回空列表表示解析失败
        }
    }
    @PostMapping("/addcollege")
    public ResponseEntity<String> addcollege(@RequestBody CollegeDTO collegeDTO) {
        try {
                if (collegeDTO.getUsername() != null) {
                    // 将 username、password、role 插入到 user 表
                    User user = new User();
                    user.setUsername(collegeDTO.getUsername());
                    user.setPassword("000000"); // 需要注意密码策略
                    user.setRole(3);

                    userService.save(user);

                    // 获取新增记录的 id
                    Integer userId = user.getId();

                    // 将剩余字段插入到对应表
                    College college = new College();
                    college.setUserId(userId);
                    college.setCollegeNo(collegeDTO.getCollegeNo());
                    college.setCollegeName(collegeDTO.getCollegeName());
                    college.setUser(collegeDTO.getUser());
                    college.setAuditor(collegeDTO.getAuditor());

                    collegeService.save(college);
                }

            return ResponseEntity.ok("Data inserted into User and College tables successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the JSON data.");
        }
    }
    @PostMapping("/addstudent")
    public ResponseEntity<String> addstudent(@RequestBody StudentDTO studentDTO) {
        try {
            if (studentDTO.getUsername() != null) {
                // 将 username、password、role 插入到 user 表
                User user = new User();
                user.setUsername(studentDTO.getUsername());
                user.setPassword("000000"); // 需要注意密码策略
                user.setRole(7);

                userService.save(user);

                // 获取新增记录的 id
                Integer userId = user.getId();

                // 将剩余字段插入到对应表
                Student student = new Student();
                student.setUserId(userId);
                student.setStudentNo(studentDTO.getStudentNo());
                student.setStudentName(studentDTO.getStudentName());
                student.setSex((byte) studentDTO.getSex());
                student.setBelongCollege(studentDTO.getBelongCollege());

                studentService.save(student);

                return ResponseEntity.ok("Data inserted into User and Student tables successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid input data. Please provide valid information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the JSON data.");
        }
    }
    @PostMapping("/addfirstLvDiscipline")
    public ResponseEntity<String> addfirstLvDiscipline(@RequestBody FirstLvDisciplineDTO firstLvDisciplineDTO) {
        try {
            if (firstLvDisciplineDTO.getUsername() != null) {
                // 将 username、password、role 插入到 user 表
                User user = new User();
                user.setUsername(firstLvDisciplineDTO.getUsername());
                user.setPassword("000000"); // 需要注意密码策略
                user.setRole(4);

                userService.save(user);

                // 获取新增记录的 id
                Integer userId = user.getId();

                // 将剩余字段插入到对应表
                FirstLvDiscipline firstLvDiscipline = new FirstLvDiscipline();
                firstLvDiscipline.setUserId(userId);
                firstLvDiscipline.setFirstLvDisciplineNo(firstLvDisciplineDTO.getFirstLvDisciplineNo());
                firstLvDiscipline.setFirstLvDisciplineName(firstLvDisciplineDTO.getFirstLvDisciplineName());

                firstLvDisciplineService.save(firstLvDiscipline);

                return ResponseEntity.ok("Data inserted into User and FirstLvDiscipline tables successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid input data. Please provide valid information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the JSON data.");
        }
    }
    @PostMapping("/addsecondLvDiscipline")
    public ResponseEntity<String> addsecondLvDiscipline(@RequestBody SecondLvDisciplineDTO secondLvDisciplineDTO) {
        try {
            if (secondLvDisciplineDTO.getUsername() != null) {
                // 将 username、password、role 插入到 user 表
                User user = new User();
                user.setUsername(secondLvDisciplineDTO.getUsername());
                user.setPassword("000000"); // 需要注意密码策略
                user.setRole(5);

                userService.save(user);

                // 获取新增记录的 id
                Integer userId = user.getId();

                // 将剩余字段插入到对应表
                SecondLvDiscipline secondLvDiscipline = new SecondLvDiscipline();
                secondLvDiscipline.setUserId(userId);
                secondLvDiscipline.setSecondLvDisciplineNo(secondLvDisciplineDTO.getSecondLvDisciplineNo());
                secondLvDiscipline.setSecondLvDisciplineName(secondLvDisciplineDTO.getSecondLvDisciplineName());
                secondLvDiscipline.setBelongFirstLvDisciplineNo(secondLvDisciplineDTO.getBelongFirstLvDisciplineNo());

                secondLvDisciplineService.save(secondLvDiscipline);

                return ResponseEntity.ok("Data inserted into User and SecondLvDiscipline tables successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid input data. Please provide valid information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the JSON data.");
        }
    }
    @PostMapping("/addsupervisor")
    public ResponseEntity<String> addsupervisor(@RequestBody SupervisorDTO supervisorDTO) {
        try {
            if (supervisorDTO.getUsername() != null) {
                // 将 username、password、role 插入到 user 表
                User user = new User();
                user.setUsername(supervisorDTO.getUsername());
                user.setPassword("000000"); // 需要注意密码策略
                user.setRole(6);

                userService.save(user);

                // 获取新增记录的 id
                Integer userId = user.getId();

                // 将剩余字段插入到对应表
                Supervisor supervisor = new Supervisor();
                supervisor.setUserId(userId);
                supervisor.setSupervisorNo(supervisorDTO.getSupervisorNo());
                supervisor.setSupervisorName(supervisorDTO.getSupervisorName());

                supervisorService.save(supervisor);

                return ResponseEntity.ok("Data inserted into User and Supervisor tables successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid input data. Please provide valid information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，这里简单打印了异常堆栈
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while processing the JSON data.");
        }
    }

}





