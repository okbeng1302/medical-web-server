package com.medical.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.medical.domain.User;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM `docdemo`.`user` where userName = #{userName} and password = #{password} and state = 1;")
    User findByNameAndPassword(User user);

    @Insert("INSERT INTO `docdemo`.`user` (`id`, `userName`, `password`, `realName`, `age`, `phoneNumber`, `headPicture`, `addDate`, `updateDate`, `state`) VALUES (null, #{userName}, #{password}, #{realName}, #{age}, #{phoneNumber}, #{headPicture}, now(), now(), 1);")
    int insert(User user);

    @Select("SELECT * FROM `docdemo`.`user` where userName = #{userName};")
    User findAdminByName(String userName);
}
