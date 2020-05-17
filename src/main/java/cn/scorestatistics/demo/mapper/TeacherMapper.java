package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbTeacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    long countByTeacher();

    long countByRemoveTeacher();

    List<TbTeacher> selectByTeacherInfo(@Param("search")String search);

    List<TbTeacher> selectByRemoveTeacherInfo(@Param("search")String search);

    List<TbTeacher> selectByUsername(@Param("username")String username);

    TbTeacher selectByPrimaryKey(@Param("id")Long id);

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("tbTeacher")TbTeacher tbTeacher);

    int updateByPrimaryKey(@Param("tbTeacher")TbTeacher tbTeacher);
}
