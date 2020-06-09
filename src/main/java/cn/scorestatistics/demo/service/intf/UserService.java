package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.dto.front.UserDto;
import cn.scorestatistics.demo.model.entity.TbUser;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    /**
     * 根据ID获取用户信息
     * @param studentId
     * @return
     */
    TbUser getUserById(long studentId);

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    TbUser addUser(UserDto userDto);

    /**
     * 根据用户名获取
     * @param username
     * @return
     */
    TbUser getUserByUsername(String username);

    /**
     * 修改用户role_id
     * @param username
     * @param roleId
     * @return
     */
    TbUser alertUserRoleId(String username, Integer roleId);

    /**
     * 修改用户状态
     * @param username
     * @param state
     * @return
     */
    TbUser alertUserState(String username, Integer state);

    /**
     * 通过用户名获取角色
     * @param username
     * @return
     */
    Set<String> getRoles(String username);

    /**
     * 通过用户名获取权限
     * @param username
     * @return
     */
    Set<String> getPermissions(String username);

}
