package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.dto.front.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User userLogin(String username, String password);

    /**
     * 通过token获取
     * @param token
     * @return
     */
    User getUserByToken(String token);

    /**
     * 注销
     * @param token
     * @return
     */
    int logout(String token);
}
