package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.entity.TbLog;
import cn.scorestatistics.demo.model.entity.TbShiroFilter;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SystemService {

    /**
     * 获得shiro过滤链配置
     * @return
     */
    List<TbShiroFilter> getShiroFilter();

    /**
     * 添加日志
     * @param tbLog
     * @return
     */
    int addLog(TbLog tbLog);

    /**
     * 获得日志列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getLogList(int draw, int start, int length, String search);

    /**
     * 统计日志数量
     * @return
     */
    Long countLog();

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLog(Long id);
}
