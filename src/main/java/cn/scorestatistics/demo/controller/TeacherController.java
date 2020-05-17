package cn.scorestatistics.demo.controller;



import cn.scorestatistics.demo.model.dto.front.TeacherDto;
import cn.scorestatistics.demo.model.entity.TbTeacher;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.TeacherService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(description = "教师管理")
public class TeacherController {
    @Autowired
     private TeacherService teacherService;

    @RequestMapping(value = "/teacher/list", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取学生列表")
    public DataTablesResult getTeahcerList(int draw, int start, int length, String searchKey) {

            DataTablesResult   result = teacherService.getTeacherList(draw, start, length, searchKey);

            return result;
    }

    @RequestMapping(value = "/teacher/list/remove", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取学生列表")
    public DataTablesResult getRemoveTeachertList(int draw, int start, int length, String searchKey) {

        DataTablesResult result = teacherService.getRemoveTeacherList(draw, start, length, searchKey);

        return result;
    }


    @RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加教师")
    public Result<TbTeacher> createStudent(@RequestBody TeacherDto teacherDto) {

        TbTeacher newTbTeacher = teacherService.addTeacher(teacherDto);

        return new ResultUtil<TbTeacher>().setData(newTbTeacher);
    }

    @RequestMapping(value = "/teacher/stop", method = RequestMethod.PUT)
    @ApiOperation(value = "停用教师")
    public Result<TbTeacher> stopStudent(Long id) {

        TbTeacher tbStudent = teacherService.alertTeacherState(id, 0);

        return new ResultUtil<TbTeacher>().setData(tbStudent);
    }

    @RequestMapping(value = "/teacher/start", method = RequestMethod.PUT)
    @ApiOperation(value = "启用教师")
    public Result<TbTeacher> startStudent(Long[] ids) {

        for(Long id:ids) {
            teacherService.alertTeacherState(id, 1);
        }
        return new ResultUtil<TbTeacher>().setData(null);
    }

    @RequestMapping(value = "/teacher/remove", method = RequestMethod.PUT)
    @ApiOperation(value = "移除教师")
    public Result<TbTeacher> removeStudent(Long[] ids) {

        for(Long id:ids) {
            teacherService.alertTeacherState(id, 2);
        }
        return new ResultUtil<TbTeacher>().setData(null);
    }

    @RequestMapping(value = "/teacher/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "彻底删除教师")
    public Result<TbTeacher> deleteStudent(Long[] ids) {
        for(Long id:ids) {
            System.out.println(id);
            teacherService.deleteTeacher(id);
        }
        return new ResultUtil<TbTeacher>().setData(null);
    }

    @RequestMapping(value = "/teacher/username", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名获取学生信息")
    public Result<TbTeacher> getStudentByUsername(String username) {

        TbTeacher tbTeacher = teacherService.getTeacherByUsername(username);

        return new ResultUtil<TbTeacher>().setData(tbTeacher);
    }


}
