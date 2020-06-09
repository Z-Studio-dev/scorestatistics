package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.entity.TbFractionLog;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FractionService {

    /**
     * 添加分数日志
     * @param tbFractionLog
     * @return
     */
    int addLog(TbFractionLog tbFractionLog);

    /**
     * 获得分数日志列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getFractionLogList(int draw, int start, int length, String search, List<String> managerClass);

    /**
     * 统计分数日志数量
     * @return
     */
    Long countFractionLog();

    /**
     * 删除分数日志
     * @param id
     * @return
     */
    int deleteLog(Long id);

}
