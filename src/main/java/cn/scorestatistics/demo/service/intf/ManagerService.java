package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.dto.front.User;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {

    /**
     * 根据ID获取管理员信息
     * @param managerId
     * @return
     */
    TbUser getManagerById(long managerId);

    /**
     * 分页获得管理员列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getManagerList(int draw, int start, int length, String search);

    /**
     * 获得所有管理员总数
     * @return
     */
    DataTablesResult getManagerCount();

    /**
     * 修改管理员状态
     * @param id
     * @param state
     * @return
     */
    TbUser alertManagerState(Long id, Integer state);

    /**
     * 删除管理员
     * @param id
     * @return
     */
    int deleteManager(Long id);
}
