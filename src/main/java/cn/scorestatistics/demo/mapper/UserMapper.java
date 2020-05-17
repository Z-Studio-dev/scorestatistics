package cn.scorestatistics.demo.mapper;

import cn.scorestatistics.demo.model.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    TbUser findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    List<TbUser> selectByUsername(@Param("username")String username);

    TbUser selectByPrimaryKey(@Param("id")Long id);

    TbUser findByUsername(@Param("username")String username);

    int updateByPrimaryKey(@Param("tbUser")TbUser tbUser);

    int updateRoleByUsername(@Param("tbUser")TbUser tbUser);

    int insert(@Param("tbUser")TbUser tbUser);
}
