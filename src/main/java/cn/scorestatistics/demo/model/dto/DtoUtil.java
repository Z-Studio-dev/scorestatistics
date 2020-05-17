package cn.scorestatistics.demo.model.dto;

import cn.scorestatistics.demo.model.dto.front.StudentDto;
import cn.scorestatistics.demo.model.dto.front.TeacherDto;
import cn.scorestatistics.demo.model.dto.front.User;
import cn.scorestatistics.demo.model.dto.front.UserDto;
import cn.scorestatistics.demo.model.entity.TbStudent;
import cn.scorestatistics.demo.model.entity.TbTeacher;
import cn.scorestatistics.demo.model.entity.TbUser;

public class DtoUtil {

    public static User TbUserToUser(TbUser tbUser) {

        User user = new User();

        user.setId(tbUser.getId());
        user.setUsername(tbUser.getUsername());
        user.setNickname(tbUser.getNickname());
        user.setSex(tbUser.getSex());
        user.setFile(tbUser.getFile());
        user.setDescription(tbUser.getDescription());
        user.setRole_id(tbUser.getRole_id());

        return user;
    }

    public static TbUser UserDtoToTbUser(UserDto userDto) {

        TbUser tbUser = new TbUser();

        if(!userDto.getUsername().isEmpty()) {
            tbUser.setUsername(userDto.getUsername());
        }
        if(!userDto.getPassword().isEmpty()) {
            tbUser.setPassword(userDto.getPassword());
        }
        if(!userDto.getNickname().isEmpty()) {
            tbUser.setNickname(userDto.getNickname());
        }
        if(!userDto.getSex().isEmpty()) {
            tbUser.setSex(userDto.getSex());
        }
        if(!userDto.getDescription().isEmpty()) {
            tbUser.setDescription(userDto.getDescription());
        }

        return tbUser;
    }

    public static TbStudent StudentDtoToStudent(StudentDto studentDto) {

        TbStudent tbStudent = new TbStudent();

        if(!studentDto.getUsername().isEmpty()) {
            tbStudent.setUsername(studentDto.getUsername());
        }
        if(!studentDto.getPassword().isEmpty()) {
            tbStudent.setPassword(studentDto.getPassword());
        }
        if(!studentDto.getNickname().isEmpty()) {
            tbStudent.setNickname(studentDto.getNickname());
        }
        if(!studentDto.getInclass().isEmpty()) {
            tbStudent.setInclass(studentDto.getInclass());
        }
        if(!studentDto.getSex().isEmpty()) {
            tbStudent.setSex(studentDto.getSex());
        }
        if(!studentDto.getDescription().isEmpty()) {
            tbStudent.setDescription(studentDto.getDescription());
        }

        return tbStudent;
    }

    public static StudentDto TbStudentToStudent(TbStudent tbStudent) {

        StudentDto studentDto = new StudentDto();

        studentDto.setUsername(tbStudent.getUsername());
        studentDto.setNickname(tbStudent.getNickname());
        studentDto.setInclass(tbStudent.getInclass());
        studentDto.setFraction(tbStudent.getFraction());
        studentDto.setFraction_change(tbStudent.getFraction_change());
        studentDto.setSex(tbStudent.getSex());
        studentDto.setFile(tbStudent.getFile());
        studentDto.setDescription(tbStudent.getDescription());

        return studentDto;
    }

    public static TbTeacher TeacherDtoToTeacher(TeacherDto teacherDto) {

        TbTeacher tbTeacher = new TbTeacher();

        if(!teacherDto.getUsername().isEmpty()) {
            tbTeacher.setUsername(teacherDto.getUsername());
        }
        if(!teacherDto.getPassword().isEmpty()) {
            tbTeacher.setPassword(teacherDto.getPassword());
        }
        if(!teacherDto.getNickname().isEmpty()) {
            tbTeacher.setNickname(teacherDto.getNickname());
        }
        if(!teacherDto.getManagement_class().isEmpty()) {
            tbTeacher.setManagement_class(teacherDto.getManagement_class());
        }
        if(!teacherDto.getSex().isEmpty()) {
            tbTeacher.setSex(teacherDto.getSex());
        }
        if(!teacherDto.getDescription().isEmpty()) {
            tbTeacher.setDescription(teacherDto.getDescription());
        }

        return tbTeacher;
    }

    public static TeacherDto TbTeacherToTeacher(TbTeacher tbTeacher) {

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setUsername(tbTeacher.getUsername());
        teacherDto.setNickname(tbTeacher.getNickname());
        teacherDto.setPassword(tbTeacher.getPassword());
        teacherDto.setManagement_class(tbTeacher.getManagement_class());
        teacherDto.setSex(tbTeacher.getSex());
        teacherDto.setFile(tbTeacher.getFile());
        teacherDto.setDescription(tbTeacher.getDescription());

        return teacherDto;
    }

}
