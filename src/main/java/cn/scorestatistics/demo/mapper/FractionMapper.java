package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.Fraction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FractionMapper {


     // 根据id查询成绩
     Fraction findById(@Param("id") long id);

     //根据姓名查询成绩
     List<Fraction> findByName(@Param("username") String username);

    //根据姓名模糊查询
    List<Fraction> selectByName(@Param("username") String username);
        
    //查询同一班级所有学生的信息
    List<Fraction> findByClassName(@Param("classname") String classname);

    //加分
    int bouns(@Param("num") long num, @Param("id") long id);

    //减分
    int Subtraction(@Param("num") long num, @Param("id") long id);

    //点击一次加一分
    int  oneBouns(@Param("id") long id);

    //点击一次减一分
    int  oneSubtraction(@Param("id") long id);

}
