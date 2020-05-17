package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.dto.front.StudentDto;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    /**
     * 根据ID获取学生信息
     * @param studentId
     * @return
     */
    TbStudent getStudentById(long studentId);

    /**
     * 分页获得学生列表
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getStudentList(int draw, int start, int length, String search);

    /**
     * 分页获得移除学生列表
     * @param start
     * @param length
     * @param search
     * @return
     */
    DataTablesResult getRemoveStudentList(int draw, int start, int length, String search);

    /**
     * 根据班级名称分页获得学生列表
     * @param draw
     * @param start
     * @param length
     * @param className
     * @return
     */
    DataTablesResult getStudentListByClassName(int draw, int start, int length, String className, String sortColumn, String sort);

    /**
     * 获得所有学生总数
     * @return
     */
    DataTablesResult getStudentCount();

    /**
     * 获得移除学生总数
     * @return
     */
    DataTablesResult getRemoveStudentCount();

    /**
     * 根据班级名获得学生总数
     * @param className
     * @return
     */
    int getStudentCountByClassName(String className);

    /**
     * 通过用户名获取
     * @param username
     * @return
     */
    TbStudent getStudentByUsername(String username);

    /**
     * 新增学生
     * @param studentDto
     * @return
     */
    TbStudent addStudent(StudentDto studentDto);

    /**
     * 修改学生状态
     * @param id
     * @param state
     * @return
     */
    TbStudent alertStudentState(Long id, Integer state);

    /**
     * 彻底删除学生
     * @param id
     * @return
     */
    int deleteStudent(Long id);

    /**
     * 修改学生分数
     * @param id
     * @param fraction
     * @return
     */
    TbStudent alertStudentFraction(Long id, Integer fraction);

    /**
     * 修改学生role_id（班级管理员）
     * @param id
     * @param role_id
     * @return
     */
    TbStudent alertStudentRole(Long id, Integer role_id);
}
