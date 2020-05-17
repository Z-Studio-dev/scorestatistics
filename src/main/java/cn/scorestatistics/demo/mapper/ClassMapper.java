package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.Classmanager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper {

    // 查询所有班级
    List<Classmanager> findAll();

    // 根据id查询班级
    Classmanager findById(@Param("id")long id);

    // 根据班级名查询班级
    Classmanager findByClassName(@Param("classname")String classname);

    // 根据年级查询班级
    List<Classmanager> findAllByGrade(@Param("grade")int grade);

    // 根据专业查询班级
    List<Classmanager> findAllByProfession(@Param("profession")String profession);

    // 新增班级
    int insert(@Param("classmanager")Classmanager classmanager);

    // 更新班级
    int update(@Param("classmanager")Classmanager classmanager);

    // 删除班级
    int del(@Param("id")long id);

    // 禁用/启用 班级
    int disenaCLass(@Param("id")long id, @Param("state")int state);

}
