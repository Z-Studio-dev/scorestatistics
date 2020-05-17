package cn.scorestatistics.demo.service.intf;

import org.springframework.stereotype.Service;

@Service
public interface RegisterService {

    /**
     * 注册
     * @param userName
     * @param userPwd
     * @return
     */
    int register(String userName, String userPwd);
}
