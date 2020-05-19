package cn.scorestatistics.demo.utils;

import cn.scorestatistics.demo.exception.ScoreException;
import cn.scorestatistics.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
public class TimerUtil {

    @Autowired
    StudentMapper studentMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStudentFraction() {

        try{
            // 每日凌晨0点更新学生今日所获得的分数
            List<Long> list = studentMapper.selectIdByState(1);

            studentMapper.updateFractionChange(list);
        }catch (Exception e) {
            throw new ScoreException("更新学生每日分数失败");
        }
    }
}
