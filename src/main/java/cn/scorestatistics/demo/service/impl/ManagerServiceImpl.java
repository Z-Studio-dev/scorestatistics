package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.ManagerMapper;
import cn.scorestatistics.demo.model.entity.TbUser;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.service.intf.ManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public TbUser getManagerById(long managerId) {

        TbUser tbUser;
        try{
            tbUser = managerMapper.selectByPrimaryKey(managerId);
        } catch (Exception e) {
            throw new ScoreException("ID获取管理员信息失败");
        }
        tbUser.setPassword("");
        return tbUser;
    }

    @Override
    public DataTablesResult getManagerList(int draw, int start, int length, String search) {

        DataTablesResult result = new DataTablesResult();

        try{
            PageHelper.startPage(start, length);
            List<TbUser> list = managerMapper.selectByManagerInfo("%"+search+"%");
            PageInfo<TbUser> pageInfo = new PageInfo<>(list);

            for(TbUser tbUser:list) {
                tbUser.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getManagerCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);
        } catch (Exception e) {
            throw new ScoreException("加载管理员列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getManagerCount() {

        DataTablesResult result = new DataTablesResult();
        try{
            result.setRecordsTotal((int)managerMapper.countByManager());
        } catch (Exception e) {
            throw new ScoreException("同i就管理员数失败");
        }
        return result;
    }

    @Override
    public TbUser alertManagerState(Long id, Integer state) {

        TbUser tbUser = managerMapper.selectByPrimaryKey(id);
        tbUser.setState(state);
        tbUser.setUpdated(new Date());

        if(managerMapper.updateByPrimaryKey(tbUser) != 1) {
            throw new ScoreException("修改管理员状态失败");
        }
        return getManagerById(id);
    }

    @Override
    public int deleteManager(Long id) {

        if(managerMapper.deleteByPrimaryKey(id) != 1) {
            throw new ScoreException("删除管理员失败");
        }
        return 0;
    }
}
