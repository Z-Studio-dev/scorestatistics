package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.dto.front.TeacherDto;
import cn.scorestatistics.demo.model.entity.TbTeacher;
import cn.scorestatistics.demo.model.pojo.DataTablesResult;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    DataTablesResult getTeacherList(int draw, int start, int length, String search);


    DataTablesResult getRemoveTeacherList(int draw, int start, int length, String search);


    DataTablesResult getTeacherCount();

    DataTablesResult getRemoveTeacherCount();


    TbTeacher alertTeacherState(Long id, Integer state);

    int deleteTeacher(Long id);

    TbTeacher addTeacher(TeacherDto teacherDto);

    TbTeacher getTeacherById(long teacherid);

    TbTeacher getTeacherByUsername(String username);
}
