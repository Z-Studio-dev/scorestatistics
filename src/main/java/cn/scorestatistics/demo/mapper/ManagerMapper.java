package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {

    long countByManager();

    List<TbUser> selectByManagerInfo(@Param("search")String search);

    List<TbUser> selectByUsername(@Param("username")String username);

    TbUser selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKey(@Param("tbStudent")TbUser tbUser);

    int deleteByPrimaryKey(@Param("id")Long id);

}
