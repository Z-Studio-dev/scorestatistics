package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.entity.Fraction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FractionService {

    /**
     * 根据id查询成绩
     * @param id
     * @return
     */
    Fraction findById(@Param("id") long id);

    /**
     * 根据名字查询成绩
     * @param username
     * @return
     */
    List<Fraction> findByName(@Param("username") String username);

    /**
     * 加分
     * @param num
     * @param id
     * @return
     */
    int jiafen(@Param("num") long num, @Param("id") long id);

    /**
     * 减分
     * @param num
     * @param id
     * @return
     */
    int Subtraction(@Param("num") long num, @Param("id") long id);

    /**
     * 根据姓名模糊查询
     * @param username
     * @return
     */
    List<Fraction> selectByName(@Param("username") String username);

    /**
     * 查询同一班级所有学生的信息
     * @param classname
     * @return
     */
    List<Fraction> findByClassName(@Param("classname") String classname);

    /**
     * 点击一次加一分
     * @param id
     * @return
     */
    int oneBouns(@Param("id")long id);

    /**
     * 点击一次减一分
     * @param id
     * @return
     */
    int oneSubtraction(@Param("id")long id);

}
