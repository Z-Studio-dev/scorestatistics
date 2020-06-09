package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.UserMapper;
import cn.scorestatistics.demo.model.dto.DtoUtil;
import cn.scorestatistics.demo.model.dto.front.UserDto;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public TbUser getUserById(long userId) {

        TbUser tbUser;
        try{
            tbUser = userMapper.selectByPrimaryKey(userId);
        } catch (Exception e) {
            throw new ScoreException("ID获取用户信息失败");
        }
        tbUser.setPassword("");
        return tbUser;
    }

    @Override
    public TbUser addUser(UserDto userDto) {

        TbUser tbUser = DtoUtil.UserDtoToTbUser(userDto);

        if(getUserByUsername(userDto.getUsername()) != null) {
            throw new ScoreException("用户名已被注册");
        }

        String md5Pass = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(md5Pass);
        tbUser.setUsername(tbUser.getUsername());
        tbUser.setNickname(tbUser.getNickname());
        tbUser.setState(1);
        tbUser.setRole_id(userDto.getRole_id());
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        if(userMapper.insert(tbUser) != 1) {
            throw new ScoreException("添加用户失败");
        }
        return getUserByUsername(tbUser.getUsername());
    }

    @Override
    public TbUser getUserByUsername(String username) {

        List<TbUser> list;
        try{
            list = userMapper.selectByUsername(username);
        } catch (Exception e) {
            throw new ScoreException("用户名获取用户失败");
        }
        if(!list.isEmpty()) {
//            list.get(0).setPassword("");
            return list.get(0);
        }
        return null;
    }

    @Override
    public TbUser alertUserRoleId(String username, Integer roleId) {

        TbUser tbUser;
        try{
            tbUser = userMapper.findByUsername(username);
        } catch (Exception e) {
            throw new ScoreException("用户名获取用户失败");
        }
        if(tbUser != null) {
            tbUser.setUsername(username);
            tbUser.setRole_id(roleId);
            tbUser.setUpdated(new Date());

            if(userMapper.updateRoleByUsername(tbUser) != 1) {
                throw new ScoreException("修改用户身份失败");
            }
        }
        return null;
    }

    @Override
    public TbUser alertUserState(String username, Integer state) {
        TbUser tbUser = userMapper.findByUsername(username);
        tbUser.setState(state);
        tbUser.setUpdated(new Date());

        if(userMapper.updateByPrimaryKey(tbUser) != 1) {
            throw new ScoreException("修改学生状态失败");
        }
        return null;
    }

    @Override
    public Set<String> getRoles(String username) {

        return userMapper.getRoles(username);
    }

    @Override
    public Set<String> getPermissions(String username) {

        return userMapper.getPermissions(username);
    }
}
