package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.annotation.FractionControllerLog;
import cn.scorestatistics.demo.model.dto.front.StudentDto;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.StudentService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "学生管理")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取学生列表")
    public DataTablesResult getStudentList(int draw, int start, int length, String searchKey) {

        DataTablesResult result = studentService.getStudentList(draw, start, length, searchKey);

        return result;
    }

    @RequestMapping(value = "/student/list/remove", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取学生移除列表")
    public DataTablesResult getRemoveStudentList(int draw, int start, int length, String searchKey) {

        DataTablesResult result = studentService.getRemoveStudentList(draw, start, length, searchKey);

        return result;
    }

    @RequestMapping(value = "/student/list/classname", method = RequestMethod.GET)
    @ApiOperation(value = "分页根据班级搜索获取学生列表")
    public DataTablesResult getStudentListByClassName(int draw, int start, int length, String className, String sortColumn, String sort) {

        DataTablesResult result = studentService.getStudentListByClassName(draw, start, length, className, sortColumn, sort);

        return result;
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加学生")
    public Result<TbStudent> createStudent(@RequestBody StudentDto studentDto) {

        TbStudent newTbStudent = studentService.addStudent(studentDto);

        return new ResultUtil<TbStudent>().setData(newTbStudent);
    }

    @RequestMapping(value = "/student/stop", method = RequestMethod.PUT)
    @ApiOperation(value = "停用学生")
    public Result<TbStudent> stopStudent(Long id) {

        TbStudent tbStudent = studentService.alertStudentState(id, 0);

        return new ResultUtil<TbStudent>().setData(tbStudent);
    }

    @RequestMapping(value = "/student/start", method = RequestMethod.PUT)
    @ApiOperation(value = "启用学生")
    public Result<TbStudent> startStudent(Long[] ids) {

        for(Long id:ids) {
            studentService.alertStudentState(id, 1);
        }
        return new ResultUtil<TbStudent>().setData(null);
    }

    @RequestMapping(value = "/student/remove", method = RequestMethod.PUT)
    @ApiOperation(value = "移除学生")
    public Result<TbStudent> removeStudent(Long[] ids) {

        for(Long id:ids) {
            studentService.alertStudentState(id, 2);
        }
        return new ResultUtil<TbStudent>().setData(null);
    }

    @RequestMapping(value = "/student/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "彻底删除学生")
    public Result<TbStudent> deleteStudent(Long[] ids) {

        for(Long id:ids) {
            studentService.deleteStudent(id);
        }
        return new ResultUtil<TbStudent>().setData(null);
    }

    @RequestMapping(value = "/student/username", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名获取学生信息")
    public Result<TbStudent> getStudentByUsername(String username) {

        TbStudent tbStudent = studentService.getStudentByUsername(username);

        return new ResultUtil<TbStudent>().setData(tbStudent);
    }

    @RequestMapping(value = "/student/fraction", method = RequestMethod.PUT)
    @ApiOperation(value = "修改学生分数")
    @FractionControllerLog(description = "修改分数")
    public Result<TbStudent> changeScore(Long id, Integer fraction, String operator) {

        TbStudent tbStudent = studentService.alertStudentFraction(id, fraction);

        return new ResultUtil<TbStudent>().setData(tbStudent);
    }

    @RequestMapping(value = "/student/manager", method = RequestMethod.PUT)
    @ApiOperation(value = "修改学生身份")
    public Result<TbStudent> changeRole(Long id, Integer roleId) {

        TbStudent tbStudent = studentService.alertStudentRole(id, roleId);

        return new ResultUtil<TbStudent>().setData(tbStudent);
    }

    @RequestMapping(value = "/student/count/class", method = RequestMethod.GET)
    @ApiOperation(value = "获得班级学生的总数")
    public int countStudentByClassName(String className) {

        return studentService.getStudentCountByClassName(className);
    }
}
