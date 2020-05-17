package cn.scorestatistics.demo.service.intf;

import cn.scorestatistics.demo.model.entity.Classmanager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassmanagerService {

    /**
     * 查询所有班级
     * @return
     */
    List<Classmanager> getAllClass();

    /**
     * 根据班级名称查询班级
     * @param className
     * @return
     */
    Classmanager getByClassName(String className);

    /**
     * 根据年级查询班级
     * @param grade
     * @return
     */
    List<Classmanager> getAllByGrade(int grade);

    /**
     * 根据专业查询班级
     * @param profession
     * @return
     */
    List<Classmanager> getAllByProfession(String profession);

    /**
     * 添加班级
     * @param className
     * @param grade
     * @param profession
     * @return
     */
    int addClass(String className, int grade, String profession, String description);

    /**
     * 修改班级信息
     * @param className
     * @param grade
     * @param profession
     * @return
     */
    int updateClass(String className, int grade, String profession, String description);

    /**
     * 删除班级
     * @param id
     * @return
     */
    int delClass(long id);

    /**
     * 禁用班级
     * @param id
     * @return
     */
    int disableClass(long id);

    /**
     * 启用班级
     * @param id
     * @return
     */
    int enableClass(long id);
}
