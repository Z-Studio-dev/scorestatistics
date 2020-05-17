package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.model.dto.front.ClassmanagerDto;
import cn.scorestatistics.demo.model.entity.Classmanager;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.ClassmanagerService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "班级处理")
public class ClassmanagerController {

    @Autowired
    private ClassmanagerService classmanagerService;

    @RequestMapping(value = "/class/classList", method = RequestMethod.GET)
    @ApiOperation(value = "所有班级")
    public Result<List> selAllClass(HttpServletRequest request) {
        List<Classmanager> classmanagerList = classmanagerService.getAllClass();

        return new ResultUtil<List>().setData(classmanagerList);
    }

    @RequestMapping(value = "/class/classname", method = RequestMethod.GET)
    @ApiOperation(value = "通过班级名称获得班级")
    public Result<List> selByClassByName(String[] classNames,
                                          HttpServletRequest request) {
        List<Classmanager> classmanagerList = new ArrayList<>();
        for(String name:classNames) {
            Classmanager classmanager = classmanagerService.getByClassName(name);
            classmanagerList.add(classmanager);
        }
        return new ResultUtil<List>().setData(classmanagerList);

    }

    @RequestMapping(value = "/class/grade", method = RequestMethod.POST)
    @ApiOperation(value = "通过年级获得班级")
    public Result<List> selAllClassByGrade(@RequestBody ClassmanagerDto classmanagerDto,
                                                  HttpServletRequest request) {
        List<Classmanager> classmanagerList = classmanagerService.getAllByGrade(classmanagerDto.getGrade());

        if(classmanagerList == null || classmanagerList.size() <= 0) {
            return new ResultUtil<List>().setErrorMsg("该班级不存在");
        } else {
            return new ResultUtil<List>().setData(classmanagerList);
        }

    }

    @RequestMapping(value = "/class/profession", method = RequestMethod.POST)
    @ApiOperation(value = "通过专业获得班级")
    public Result<List> selAllClassByProfession(@RequestBody ClassmanagerDto classmanagerDto,
                                                HttpServletRequest request) {
        List<Classmanager> classmanagerList = classmanagerService.getAllByProfession(classmanagerDto.getProfession());

        if(classmanagerList == null || classmanagerList.size() <= 0) {
            return new ResultUtil<List>().setErrorMsg("该班级不存在");
        } else {
            return new ResultUtil<List>().setData(classmanagerList);
        }
    }

    @RequestMapping(value = "/class/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增班级")
    public Result<Object> addClass(@RequestBody ClassmanagerDto classmanagerDto,
                                   HttpServletRequest request) {
        int result = classmanagerService.addClass(classmanagerDto.getClassName(), classmanagerDto.getGrade(), classmanagerDto.getProfession(), classmanagerDto.getDescription());

        if(result == 0) {
            return new ResultUtil<Object>().setErrorMsg("该班级以存在");
        } else if(result == -1) {
            return new ResultUtil<Object>().setErrorMsg("班级名称不能为空");
        }
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/class/del", method = RequestMethod.POST)
    @ApiOperation(value = "删除班级")
    public Result<Object> delClass(@RequestBody ClassmanagerDto classmanagerDto,
                                   HttpServletRequest request) {
        int result = classmanagerService.delClass(classmanagerDto.getId());

        if(result == 0) {
            return new ResultUtil<Object>().setErrorMsg("该班级不存在");
        } else if(result == -1) {
            return new ResultUtil<Object>().setErrorMsg("该id不存在");
        }
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/class/disable", method = RequestMethod.POST)
    @ApiOperation(value = "停用班级")
    public Result<Object> disClass(@RequestBody ClassmanagerDto classmanagerDto,
                                   HttpServletRequest request) {
        int result = classmanagerService.disableClass(classmanagerDto.getId());

        if(result == 0) {
            return new ResultUtil<Object>().setErrorMsg("该班级不存在");
        } else if(result == -1) {
            return new ResultUtil<Object>().setErrorMsg("该id不存在");
        }
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/class/enable", method = RequestMethod.POST)
    @ApiOperation(value = "启用班级")
    public Result<Object> enaClass(@RequestBody ClassmanagerDto classmanagerDto,
                                   HttpServletRequest request) {
        int result = classmanagerService.enableClass(classmanagerDto.getId());

        if(result == 0) {
            return new ResultUtil<Object>().setErrorMsg("该班级不存在");
        } else if(result == -1) {
            return new ResultUtil<Object>().setErrorMsg("该id不存在");
        }
        return new ResultUtil<Object>().setData(result);
    }

}
