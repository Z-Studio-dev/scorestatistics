package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.jedis.JedisClient;
import cn.scorestatistics.demo.mapper.UserMapper;
import cn.scorestatistics.demo.model.dto.DtoUtil;
import cn.scorestatistics.demo.model.dto.front.User;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.service.intf.LoginService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("1800")
    private Integer SESSION_EXPIRE;

    /**
     * 登录
     * 根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public User userLogin(String username, String password) {
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        TbUser tbUser = userMapper.findByUsernameAndPassword(username, md5Pass);
        if(tbUser == null) {
            User user = new User();
            user.setState(0);
            user.setMessage("用户名或密码错误");
            return user;
        }
        String token = UUID.randomUUID().toString();
        User user = DtoUtil.TbUserToUser(tbUser);
        user.setToken(token);
        user.setState(1);
        user.setMessage("登录成功");
        // 用户信息写入redis:key"SESSION:token" value:"user"
        jedisClient.set("SESSION:" + token, new Gson().toJson(user));
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        return user;
    }

    @Override
    public User getUserByToken(String token) {
        String json = jedisClient.get("SESSION:" + token);
        if(json == null) {
            User user = new User();
            user.setState(0);
            user.setMessage("用户登录已过期");
            return user;
        }
        // 重置过期时间
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        User user = new Gson().fromJson(json, User.class);
        return user;
    }

    @Override
    public int logout(String token) {

        jedisClient.del("SESSION:" + token);

        return 1;
    }

}
