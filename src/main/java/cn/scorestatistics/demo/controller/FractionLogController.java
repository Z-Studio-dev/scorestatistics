package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.model.entity.TbFractionLog;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.FractionService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(description = "分数日志管理")
public class FractionLogController {

    @Autowired
    private FractionService fractionService;

    @RequestMapping(value = "/fractionLog/list", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取分数日志列表")
    public DataTablesResult getFractionLogList(int draw, int start, int length, String searchKey) {
        List<String> managerClass = new ArrayList<>();
        DataTablesResult result = fractionService.getFractionLogList(draw, start, length, searchKey, managerClass);

        return result;
    }

    @RequestMapping(value = "/fractionLog/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "彻底删除分数日志")
    public Result<TbFractionLog> deleteFractionLog(Long[] ids) {

        for(Long id:ids) {
            fractionService.deleteLog(id);
        }
        return new ResultUtil<TbFractionLog>().setData(null);
    }

}
