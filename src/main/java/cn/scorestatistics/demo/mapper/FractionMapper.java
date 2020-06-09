package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbFractionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FractionMapper {

    long countByLog();

    List<TbFractionLog> selectByMulti(@Param("search")String search, List<String> managerClass);

    TbFractionLog selectByPrimaryKey(@Param("id")Long id);

    int insert(@Param("tbFractionLog") TbFractionLog tbFractionLog);

    int deleteByPrimaryKey(@Param("id")Long id);

}
