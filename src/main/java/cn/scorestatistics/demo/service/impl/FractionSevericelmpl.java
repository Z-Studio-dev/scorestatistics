package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.mapper.FractionMapper;
import cn.scorestatistics.demo.model.entity.Fraction;
import cn.scorestatistics.demo.service.intf.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FractionSevericelmpl implements FractionService{

    @Autowired
    private FractionMapper fractionMapper;

    /**
     * 根据id查询成绩
     * @param id
     * @return
     */
    @Override
    public Fraction findById(long id) {
        return fractionMapper.findById(id);
    }

    /**
     * 根据名字查询成绩
     * @param username
     * @return
     */
    @Override
    public List<Fraction> findByName(String username) { return fractionMapper.findByName(username);}

    /**
     * 根据姓名模糊查询
     * @param username
     * @return
     */
    @Override
    public List<Fraction> selectByName(String username) { return fractionMapper.selectByName(username); }


    /**
     * 查询同一班级所有学生的信息
     * @param classname
     * @return
     */
    @Override
    public List<Fraction> findByClassName(String classname) { return fractionMapper.findByClassName(classname); }

    /**
     * 加分
     * @param num
     * @param id
     * @return
     */
    @Override
    public int jiafen(long num, long id) { return fractionMapper.bouns(num,id); }


    /**
     * 减分
     * @param num
     * @param id
     * @return
     */
    @Override
    public int Subtraction(long num, long id) {return fractionMapper.Subtraction(num,id);
    }

    /**
     * 点击一次加一分
     * @param id
     * @return
     */
    @Override
    public int oneBouns(long id) {
        return fractionMapper.oneBouns(id);
    }


    /**
     * 点击一次减一分
     * @param id
     * @return
     */
    @Override
    public int oneSubtraction(long id) { return fractionMapper.oneSubtraction(id); }
}
