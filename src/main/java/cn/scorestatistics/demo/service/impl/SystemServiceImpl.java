package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.LogMapper;
import cn.scorestatistics.demo.model.entity.TbLog;
import cn.scorestatistics.demo.model.entity.TbShiroFilter;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.service.intf.SystemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<TbShiroFilter> getShiroFilter() {
        return null;
    }

    @Override
    public int addLog(TbLog tbLog) {

        if(logMapper.insert(tbLog) != 1) {
            throw new ScoreException("保存日志失败");
        }
        return 1;
    }

    @Override
    public DataTablesResult getLogList(int draw, int start, int length, String search) {

        DataTablesResult result = new DataTablesResult();
        // 分页
        PageHelper.startPage(start, length);
        List<TbLog> list = logMapper.selectByMulti("%"+search+"%");
        PageInfo<TbLog> pageInfo = new PageInfo<>(list);

        result.setRecordsTotal((int)pageInfo.getTotal());
        result.setRecordsFiltered(Math.toIntExact(countLog()));

        result.setDraw(draw);
        result.setData(list);

        return result;
    }

    @Override
    public Long countLog() {

        Long result = logMapper.countByLog();
        if(result == null) {
            throw new ScoreException("获取日志数量失败");
        }
        return result;
    }

    @Override
    public int deleteLog(Long id) {

        if(logMapper.deleteByPrimaryKey(id) != 1) {
            throw new ScoreException("删除日志失败");
        }
        return 1;
    }
}
