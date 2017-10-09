package com.shiny.tenant.dao;

import com.shiny.tenant.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select id,`name`,pwd from `user` where `name`=#{name} and pwd=#{pwd}")
    User selectByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);

}
