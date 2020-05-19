package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    long countByStudent();

    long countByRemoveStudent();

    long countByClassName(@Param("className")String className);

    List<TbStudent> selectByStudentInfo(@Param("search")String search);

    List<TbStudent> selectByRemoveStudentInfo(@Param("search")String search);

    List<TbStudent> selectStudentInfoByClassName(@Param("classname")String className, @Param("sortColumn")String sortColumn, @Param("sort")String sort);

    List<TbStudent> selectByUsername(@Param("username")String username);

    TbStudent selectByPrimaryKey(@Param("id")Long id);

    List<Long> selectIdByState(@Param("state")int state);

    int updateByPrimaryKey(@Param("tbStudent")TbStudent tbStudent);

    int updateFractionByPrimaryKey(@Param("tbStudent")TbStudent tbStudent);

    int updateFractionChange(List<Long> list);

    int updateRoleByPrimaryKey(@Param("tbStudent")TbStudent tbStudent);

    int insert(@Param("tbStudent")TbStudent tbStudent);

    int deleteByPrimaryKey(@Param("id")Long id);
}
