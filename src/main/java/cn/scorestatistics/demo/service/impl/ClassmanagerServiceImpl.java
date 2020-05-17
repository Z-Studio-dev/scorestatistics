package cn.scorestatistics.demo.service.impl;

import cn.scorestatistics.demo.exception.ClassmanagerException;
import cn.scorestatistics.demo.mapper.ClassMapper;
import cn.scorestatistics.demo.model.entity.Classmanager;
import cn.scorestatistics.demo.service.intf.ClassmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassmanagerServiceImpl implements ClassmanagerService {

    @Autowired
    private ClassMapper classMapper;

    /**
     * 查询所有班级
     * @return
     */
    @Override
    public List<Classmanager> getAllClass() {
        return classMapper.findAll();
    }

    /**
     * 根据班级名称查询班级
     * @param className
     * @return
     */
    @Override
    public Classmanager getByClassName(String className) {
        return classMapper.findByClassName(className);
    }

    /**
     * 根据年级查询所有班级
     * @param grade
     * @return
     */
    @Override
    public List<Classmanager> getAllByGrade(int grade) {
        return classMapper.findAllByGrade(grade);
    }

    /**
     * 根据专业查询所有班级
     * @param profession
     * @return
     */
    @Override
    public List<Classmanager> getAllByProfession(String profession) {
        return classMapper.findAllByProfession(profession);
    }

    /**
     * 添加班级
     * @param className
     * @param grade
     * @param profession
     * @param description
     * @return
     */
    @Override
    public int addClass(String className, int grade, String profession, String description) {

        Classmanager classmanager = new Classmanager();
        classmanager.setClassname(className);

        if(className.isEmpty()) {
            return -1;  // 班级名不能为空
        }
        if(classMapper.findByClassName(className) != null) {
            return 0;   // 班级名已存在
        }

        classmanager.setGrade(grade);
        classmanager.setProfession(profession);
        classmanager.setDescription(description);
        classmanager.setState(1);
        classmanager.setCreated(new Date());
        classmanager.setUpdated(new Date());
        if(classMapper.insert(classmanager) != 1) {
            throw new ClassmanagerException("新增班级失败");
        }
        return 1;
    }

    /**
     * 更新班级信息
     * @param className
     * @param grade
     * @param profession
     * @param description
     * @return
     */
    @Override
    public int updateClass(String className, int grade, String profession, String description) {
        // TODO
        return 1;
    }

    /**
     * 删除班级
     * @param id
     * @return
     */
    @Override
    public int delClass(long id) {

        if(id <= 0) {
            return -1;
        }
        if(classMapper.findById(id) == null) {
            return 0;   //  该班级不存在
        }
        if(classMapper.del(id) != 1) {
            throw new ClassmanagerException("删除班级失败");
        }
        return 1;
    }

    @Override
    public int disableClass(long id) {
        if(id <= 0) {
            return -1;
        }
        if(classMapper.findById(id) == null) {
            return 0;
        }
        if(classMapper.disenaCLass(id ,0) != 1) {
            throw new ClassmanagerException("禁用班级失败");
        }
        return 1;
    }

    @Override
    public int enableClass(long id) {
        if(id <= 0) {
            return -1;
        }
        if(classMapper.findById(id) == null) {
            return 0;
        }
        if(classMapper.disenaCLass(id ,1) != 1) {
            throw new ClassmanagerException("启用班级失败");
        }
        return 1;
    }
}
