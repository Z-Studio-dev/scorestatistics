package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.model.entity.Fraction;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.FractionService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@Api(description = "分数操作")
public class FractionController {

    @Autowired
    private FractionService fractionService;

    @RequestMapping(value = "/fraction/findByid",method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询成绩")
    public @ResponseBody Result<Fraction> findById( long id, HttpServletRequest request) {
        Fraction fraction = fractionService.findById(id);
        if (fraction == null) {
            return new ResultUtil<Fraction>().setErrorMsg("id号错误");
        } else {
            return new ResultUtil<Fraction>().setData(fraction);
        }
    }

    @RequestMapping(value = "/fraction/findByname",method = RequestMethod.GET)
    @ApiOperation(value = "根据姓名查询")
    public @ResponseBody Result<List> findByName(String username,HttpServletRequest request){
        List<Fraction> fractionList = fractionService.findByName(username);
        if (fractionList == null){
            return new ResultUtil<List>().setErrorMsg("没有查询到该学生");
        }else {
            return new ResultUtil<List>().setData(fractionList);
        }


    }

    @RequestMapping(value = "/fraction/selectByname")
    @ApiOperation(value = "根据姓名模糊查询")
    public @ResponseBody Result<List> selectByName(String username, HttpServletRequest request){
        List<Fraction> fractionList = fractionService.selectByName(username);
        if (fractionList == null){
            return new ResultUtil<List>().setErrorMsg("没有查询到该学生");
        }else {
            return new ResultUtil<List>().setData(fractionList);
        }
    }

    @RequestMapping(value = "/fraction/findByClassname")
    @ApiOperation(value = "查询同一班级所有学生的信息")
    public @ResponseBody Result<List> findByClassName(String classname, HttpServletRequest request){
        List<Fraction> fractionList = fractionService.findByClassName(classname);
        if (fractionList == null){
            return new ResultUtil<List>().setErrorMsg("没有查询到此班级");
        }else {
            return new ResultUtil<List>().setData(fractionList);
        }
    }

    @RequestMapping(value = "/fraction/jiafen",method = RequestMethod.GET)
    @ApiOperation(value = "加分")
    public @ResponseBody int jiafen(long num,long id,HttpServletRequest request) {
        int fraction = fractionService.jiafen(num,id);
        if (fraction != 1) {
            return -1;
        } else {
            return 1;
        }
    }

    @RequestMapping(value = "/fraction/Subtraction",method = RequestMethod.GET)
    @ApiOperation(value = "减分")
    public int Subtraction(long num,long id,HttpServletRequest request) {
        int fraction = fractionService.Subtraction(num,id);
        if (fraction != 1) {
            return -1;
        } else {
            return 1;
        }
    }

    @RequestMapping(value = "/fraction/oneBouns",method = RequestMethod.GET)
    @ApiOperation(value = "加一分")
    public int oneBouns(long id, HttpServletRequest request) {
        int fraction = fractionService.oneBouns(id);
        if (fraction != 1) {
            return -1;
        } else {
            return 1;
        }
    }

    @RequestMapping(value = "/fraction/oneSubtraction",method = RequestMethod.GET)
    @ApiOperation(value = "减一分")
    public int oneSubtraction(long id,HttpServletRequest request) {
        int fraction = fractionService.oneSubtraction(id);
        if (fraction != 1) {
            return -1;
        } else {
            return 1;
        }
    }
}
