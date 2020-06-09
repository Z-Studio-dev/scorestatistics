package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.FractionMapper;
import cn.scorestatistics.demo.model.entity.TbFractionLog;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.service.intf.FractionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FractionSevericelmpl implements FractionService{

    @Autowired
    private FractionMapper fractionMapper;


    @Override
    public int addLog(TbFractionLog tbFractionLog) {
        if(fractionMapper.insert(tbFractionLog) != 1) {
            throw new ScoreException("保存分数日志失败");
        }
        return 1;
    }

    @Override
    public DataTablesResult getFractionLogList(int draw, int start, int length, String search, List<String> managerClass) {
        DataTablesResult result = new DataTablesResult();
        // 分页
        PageHelper.startPage(start, length);
        List<TbFractionLog> list = fractionMapper.selectByMulti("%"+search+"%", managerClass);
        PageInfo<TbFractionLog> pageInfo = new PageInfo<>(list);

        result.setRecordsTotal((int)pageInfo.getTotal());
        result.setRecordsFiltered(Math.toIntExact(countFractionLog()));

        result.setDraw(draw);
        result.setData(list);

        return result;
    }

    @Override
    public Long countFractionLog() {
        Long result = fractionMapper.countByLog();
        if(result == null) {
            throw new ScoreException("获取分数日志数量失败");
        }
        return result;
    }

    @Override
    public int deleteLog(Long id) {
        if(fractionMapper.deleteByPrimaryKey(id) != 1) {
            throw new ScoreException("删除分数日志失败");
        }
        return 1;
    }
}
