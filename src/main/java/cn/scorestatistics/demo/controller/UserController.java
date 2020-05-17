package cn.scorestatistics.demo.controller;

import cn.scorestatistics.demo.jedis.JedisClient;
import cn.scorestatistics.demo.model.dto.front.User;
import cn.scorestatistics.demo.model.dto.front.UserDto;
import cn.scorestatistics.demo.model.dto.front.UserLoginRegist;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.model.pojo.Result;
import cn.scorestatistics.demo.service.intf.LoginService;
import cn.scorestatistics.demo.service.intf.RegisterService;
import cn.scorestatistics.demo.service.intf.UserService;
import cn.scorestatistics.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(description = "用户注册登录")
public class UserController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<User> login(@RequestBody UserLoginRegist userLoginRegist,
                                HttpServletRequest request) {
        User user = loginService.userLogin(userLoginRegist.getUserName(), userLoginRegist.getUserPwd());

        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户")
    public Result<TbUser> createUser(@RequestBody UserDto userDto) {

        TbUser newTbUser = userService.addUser(userDto);

        return new ResultUtil<TbUser>().setData(newTbUser);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> register(@RequestBody UserLoginRegist userLoginRegist,
                                   HttpServletRequest request) {
        int result = registerService.register(userLoginRegist.getUserName(), userLoginRegist.getUserPwd());
        if (result == 0) {
            return new ResultUtil<Object>().setErrorMsg("该用户名已被注册");
        } else if (result == 1) {
            return new ResultUtil<Object>().setErrorMsg("用户名密码不能为空");
        }
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/user/checklogin", method = RequestMethod.GET)
    @ApiOperation(value = "判断用户是否登录")
    public Result<User> checkLogin(@RequestParam(defaultValue = "")String token) {
        User user = loginService.getUserByToken(token);

        return new ResultUtil<User>().setData(user);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    @ApiOperation(value = "退出登录")
    public Result<Object> logout(@RequestParam(defaultValue = "")String token) {
        loginService.logout(token);

        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户身份")
    public Result<TbUser> changeRole(String username, Integer roleId) {

        TbUser tbUser = userService.alertUserRoleId(username, roleId);

        return new ResultUtil<TbUser>().setData(tbUser);
    }

    @RequestMapping(value = "/user/stop", method = RequestMethod.PUT)
    @ApiOperation(value = "停用用户")
    public Result<TbUser> stopUser(String username) {

        TbUser tbUser = userService.alertUserState(username, 0);

        return new ResultUtil<TbUser>().setData(tbUser);
    }

    @RequestMapping(value = "/user/start", method = RequestMethod.PUT)
    @ApiOperation(value = "启用用户")
    public Result<TbUser> startUser(String username) {

        userService.alertUserState(username, 1);

        return new ResultUtil<TbUser>().setData(null);
    }
}
