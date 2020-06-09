package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {

    long countByLog();

    List<TbLog> selectByMulti(@Param("search")String search);

    TbLog selectByPrimaryKey(@Param("id")Long id);

    int insert(@Param("tbLog")TbLog tbLog);

    int deleteByPrimaryKey(@Param("id")Long id);

}
