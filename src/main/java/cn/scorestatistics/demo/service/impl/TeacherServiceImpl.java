package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.exception.TeacherException;
import cn.scorestatistics.demo.mapper.TeacherMapper;
import cn.scorestatistics.demo.model.dto.DtoUtil;
import cn.scorestatistics.demo.model.dto.front.TeacherDto;
import cn.scorestatistics.demo.model.entity.TbTeacher;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.service.intf.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public TbTeacher getTeacherById(long studentId) {

        TbTeacher tbTeacher;
        try {
            tbTeacher = teacherMapper.selectByPrimaryKey(studentId);
        } catch (Exception e) {
            throw new ScoreException("ID获取教师信息失败");
        }
        tbTeacher.setPassword("");
        return tbTeacher;
    }

    @Override
    public DataTablesResult getTeacherList(int draw, @RequestParam(name = "start",defaultValue = "1")int start,
                                           @RequestParam(name = "length",defaultValue = "10")int length, String search) {

        DataTablesResult result = new DataTablesResult();

        try{
            // 分页
            PageHelper.startPage(start, length);

            List<TbTeacher> list = teacherMapper.selectByTeacherInfo("%"+search+"%");

            PageInfo<TbTeacher> pageInfo = new PageInfo<>(list);

            for(TbTeacher tbTeacher:list) {
                tbTeacher.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getTeacherCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);

        } catch(Exception e) {
            throw new ScoreException("加载教师列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getRemoveTeacherList(int draw, int start, int length, String search) {
        DataTablesResult result = new DataTablesResult();

        try{
            // 分页
            PageHelper.startPage(start, length);
            List<TbTeacher> list = teacherMapper.selectByRemoveTeacherInfo("%"+search+"%");
            PageInfo<TbTeacher> pageInfo = new PageInfo<>(list);

            for(TbTeacher tbTeacher:list) {
                tbTeacher.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getRemoveTeacherCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);

        } catch(Exception e) {
            throw new ScoreException("加载删除教师列表失败");
        }
        return result;
}

    @Override
    public DataTablesResult getTeacherCount() {
        DataTablesResult result = new DataTablesResult();
        try{
            result.setRecordsTotal((int)teacherMapper.countByTeacher());
        } catch(Exception e) {
            throw new ScoreException("统计教师数失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getRemoveTeacherCount() {
        DataTablesResult result = new DataTablesResult();
        try{
            result.setRecordsTotal((int)teacherMapper.countByRemoveTeacher());
        } catch(Exception e) {
            throw new ScoreException("统计删除教师数失败");
        }
        return result;
    }


    @Override
    public TbTeacher alertTeacherState(Long id, Integer state) {
        TbTeacher tbTeacher = teacherMapper.selectByPrimaryKey(id);
        tbTeacher.setState(state);
        tbTeacher.setUpdated(new Date());
        if(teacherMapper.updateByPrimaryKey(tbTeacher) != 1) {
            throw new ScoreException("修改教师状态失败");
        }
        return getTeacherById(id);
    }

    @Override
    public int deleteTeacher(Long id) {
     return    teacherMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TbTeacher addTeacher(TeacherDto teacherDto) {
        TbTeacher tbTeacher = DtoUtil.TeacherDtoToTeacher(teacherDto);

        if(getTeacherByUsername(teacherDto.getUsername()) != null) {
            throw new ScoreException("用户名已被注册");
        }

        String md5Pass = DigestUtils.md5DigestAsHex(tbTeacher.getPassword().getBytes());
        tbTeacher.setPassword(md5Pass);
        tbTeacher.setUsername(tbTeacher.getUsername());
        tbTeacher.setNickname(tbTeacher.getNickname());
        tbTeacher.setManagement_class(tbTeacher.getManagement_class());
        tbTeacher.setState(1);
        tbTeacher.setRole_id(2);
        tbTeacher.setCreated(new Date());
        tbTeacher.setUpdated(new Date());

        if(teacherMapper.insert(tbTeacher) != 1) {
            throw new ScoreException("添加教师失败");
        }
        return getTeacherByUsername(tbTeacher.getUsername());
    }

    @Override
    public TbTeacher getTeacherByUsername(String username) {
        List<TbTeacher> list;
        try{
            list = teacherMapper.selectByUsername(username);
        } catch (Exception e) {
            throw new ScoreException("用户名获取学生信息失败");
        }
        if(!list.isEmpty()) {
            list.get(0).setPassword("");
            return list.get(0);
        }
        return null;
    }
}
