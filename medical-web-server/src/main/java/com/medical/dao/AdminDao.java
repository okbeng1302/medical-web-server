package com.medical.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.medical.domain.Admin;

@Mapper
public interface AdminDao {
    @Select("SELECT * FROM `docdemo`.`admin` where userName = #{userName} and password = #{password} and state = 1;")
    Admin findByNameAndPassword(Admin admin);

    @Insert("INSERT INTO `docdemo`.`admin` (`id`, `userName`, `password`, `realName`, `age`, `phoneNumber`, `headPicture`, `addDate`, `updateDate`, `state`) VALUES (null, #{userName}, #{password}, #{realName}, #{age}, #{phoneNumber}, #{headPicture}, now(), now(), 1);")
    int insert(Admin admin);

    @Select("SELECT * FROM `docdemo`.`admin` where userName = #{userName};")
    Admin findAdminByName(String userName);
}
