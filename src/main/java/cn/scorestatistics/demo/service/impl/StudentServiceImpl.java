package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.StudentMapper;
import cn.scorestatistics.demo.model.dto.DtoUtil;
import cn.scorestatistics.demo.model.dto.front.StudentDto;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import cn.scorestatistics.demo.service.intf.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public TbStudent getStudentById(long studentId) {

        TbStudent tbStudent;
        try {
            tbStudent = studentMapper.selectByPrimaryKey(studentId);
        } catch (Exception e) {
            throw new ScoreException("ID获取学生信息失败");
        }
        tbStudent.setPassword("");
        return tbStudent;
    }

    @Override
    public DataTablesResult getStudentList(int draw, @RequestParam(name = "start",defaultValue = "1")int start,
                                           @RequestParam(name = "length",defaultValue = "10")int length, String search) {

        DataTablesResult result = new DataTablesResult();

        try{
            // 分页
            PageHelper.startPage(start, length);
            List<TbStudent> list = studentMapper.selectByStudentInfo("%"+search+"%");
            PageInfo<TbStudent> pageInfo = new PageInfo<>(list);

            for(TbStudent tbStudent:list) {
                tbStudent.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getStudentCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);

        } catch(Exception e) {
            throw new ScoreException("加载学生列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getRemoveStudentList(int draw, int start, int length, String search) {

        DataTablesResult result = new DataTablesResult();

        try{
            // 分页
            PageHelper.startPage(start, length);
            List<TbStudent> list = studentMapper.selectByRemoveStudentInfo("%"+search+"%");
            PageInfo<TbStudent> pageInfo = new PageInfo<>(list);

            for(TbStudent tbStudent:list) {
                tbStudent.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getRemoveStudentCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);

        } catch(Exception e) {
            throw new ScoreException("加载删除学生列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getStudentListByClassName(int draw, int start, int length, String className, String sortColumn, String sort) {

        DataTablesResult result = new DataTablesResult();

        try{
            // 分页
            PageHelper.startPage(start, length);
            List<TbStudent> list = studentMapper.selectStudentInfoByClassName(className, sortColumn, sort);
            PageInfo<TbStudent> pageInfo = new PageInfo<>(list);

            for(TbStudent tbStudent:list) {
                tbStudent.setPassword("");
            }

            result.setRecordsTotal((int)pageInfo.getTotal());
            result.setRecordsFiltered(getStudentCount().getRecordsTotal());

            result.setDraw(draw);
            result.setData(list);

        } catch(Exception e) {
            throw new ScoreException("加载学生列表失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getStudentCount() {

        DataTablesResult result = new DataTablesResult();
        try{
            result.setRecordsTotal((int)studentMapper.countByStudent());
        } catch(Exception e) {
            throw new ScoreException("统计学生数失败");
        }
        return result;
    }

    @Override
    public DataTablesResult getRemoveStudentCount() {

        DataTablesResult result = new DataTablesResult();
        try{
            result.setRecordsTotal((int)studentMapper.countByRemoveStudent());
        } catch(Exception e) {
            throw new ScoreException("统计删除学生数失败");
        }
        return result;
    }

    @Override
    public int getStudentCountByClassName(String className) {

        int count = (int)studentMapper.countByClassName(className);

        return count;
    }

    @Override
    public TbStudent getStudentByUsername(String username) {
        List<TbStudent> list;
        try{
            list = studentMapper.selectByUsername(username);
        } catch (Exception e) {
            throw new ScoreException("用户名获取学生信息失败");
        }
        if(!list.isEmpty()) {
            list.get(0).setPassword("");
            return list.get(0);
        }
        return null;
    }

    @Override
    public TbStudent addStudent(StudentDto studentDto) {

        TbStudent tbStudent = DtoUtil.StudentDtoToStudent(studentDto);

        if(getStudentByUsername(studentDto.getUsername()) != null) {
            throw new ScoreException("用户名已被注册");
        }

        String md5Pass = DigestUtils.md5DigestAsHex(tbStudent.getPassword().getBytes());
        tbStudent.setPassword(md5Pass);
        tbStudent.setUsername(tbStudent.getUsername());
        tbStudent.setNickname(tbStudent.getNickname());
        tbStudent.setInclass(tbStudent.getInclass());
        tbStudent.setFraction_change("0");
        tbStudent.setState(1);
        tbStudent.setRole_id(1);
        tbStudent.setCreated(new Date());
        tbStudent.setUpdated(new Date());

        if(studentMapper.insert(tbStudent) != 1) {
            throw new ScoreException("添加学生失败");
        }
        return getStudentByUsername(tbStudent.getUsername());
    }

    @Override
    public TbStudent alertStudentState(Long id, Integer state) {

        TbStudent tbStudent = studentMapper.selectByPrimaryKey(id);
        tbStudent.setState(state);
        tbStudent.setUpdated(new Date());

        if(studentMapper.updateByPrimaryKey(tbStudent) != 1) {
            throw new ScoreException("修改学生状态失败");
        }
        return getStudentById(id);
    }

    @Override
    public int deleteStudent(Long id) {

        if(studentMapper.deleteByPrimaryKey(id) != 1) {
            throw new ScoreException("删除学生失败");
        }
        return 0;
    }

    @Override
    public TbStudent alertStudentFraction(Long id, Integer fraction) {

        TbStudent tbStudent = studentMapper.selectByPrimaryKey(id);
        tbStudent.setFraction(fraction);
        tbStudent.setUpdated(new Date());

        if(studentMapper.updateFractionByPrimaryKey(tbStudent) != 1){
            throw new ScoreException("修改学生分数失败");
        }
        return getStudentById(id);
    }

    @Override
    public TbStudent alertStudentRole(Long id, Integer role_id) {

        TbStudent tbStudent = studentMapper.selectByPrimaryKey(id);
        if(role_id == 1 || role_id == 3) {
            tbStudent.setRole_id(role_id);
            tbStudent.setUpdated(new Date());

            if(studentMapper.updateRoleByPrimaryKey(tbStudent) != 1) {
                throw new ScoreException("修改学生身份失败");
            }
        }else {
            throw new ScoreException("无效role_id");
        }
        return getStudentById(id);
    }
}
