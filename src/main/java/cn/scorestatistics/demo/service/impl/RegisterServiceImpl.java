package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.UserMapper;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.service.intf.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public int register(String userName, String userPwd) {

        TbUser tbUser = new TbUser();
        tbUser.setUsername(userName);

        if(userName.isEmpty() || userPwd.isEmpty()) {
            return -1;  // 用户名密码不能为空
        }
        if(userMapper.findByUsername(userName) != null) {
            return 0;   // 该用户名已被注册
        }

        // MD5加密
        String md5Pass = DigestUtils.md5DigestAsHex(userPwd.getBytes());
        tbUser.setPassword(md5Pass);
        tbUser.setState(1);
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        if(userMapper.insert(tbUser) != 1) {
            throw new ScoreException("注册用户失败");
        }
        return 1;
    }
}
