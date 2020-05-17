package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.ManagerService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "管理员管理")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/manager/list", method = RequestMethod.GET)
    @ApiOperation(value = "分页多条件搜索获取管理员列表")
    public DataTablesResult getManagerList(int draw, int start, int length, String searchKey) {

        DataTablesResult result = managerService.getManagerList(draw, start, length, searchKey);

        return result;
    }

    @RequestMapping(value = "/manager/stop", method = RequestMethod.PUT)
    @ApiOperation(value = "停用管理员")
    public Result<TbUser> stopManager(Long id) {

        TbUser tbUser = managerService.alertManagerState(id, 0);

        return new ResultUtil<TbUser>().setData(tbUser);
    }

    @RequestMapping(value = "/manager/start", method = RequestMethod.PUT)
    @ApiOperation(value = "启用管理员")
    public Result<TbUser> startManager(Long[] ids) {

        for(Long id:ids){
            TbUser tbUser = managerService.alertManagerState(id, 1);
        }
        return new ResultUtil<TbUser>().setData(null);
    }

    @RequestMapping(value = "/manager/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "彻底删除管理员")
    public Result<TbUser> deleteManager(Long[] ids) {

        for(Long id:ids) {
            managerService.deleteManager(id);
        }
        return new ResultUtil<TbUser>().setData(null);
    }
}
