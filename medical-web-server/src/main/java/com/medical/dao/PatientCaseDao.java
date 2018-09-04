package com.medical.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.medical.domain.Patientcase;
import com.medical.util.Constant;

@Mapper
public interface PatientCaseDao {

    @Insert("INSERT INTO `docdemo`.`patientcase` (`id`, `casename`, `uuid`, `occurancetime`, `maincase`, `casehis`, `diagnosis`, `suggestion`, `remark`, `createtime`) "
            + "VALUES (null, #{casename}, #{uuid}, #{occurancetime}, #{maincase}, #{casehis}, #{diagnosis}, #{suggestion}, #{remark}, #{createtime});")
    int insert(Patientcase patientcase);

    // 按照 uuid 查询用户信息
    @Select("SELECT * FROM `docdemo`.`patientcase` where uuid = #{uuid};")
    List<Patientcase> findPatientcaseByUuid(String uuid);

    // 删除
    @Delete("DELETE FROM `docdemo`.`patientcase` where uuid = #{uuid}")
    int deleteByUuid(String uuid);

    // 删除
    @Delete("DELETE FROM `docdemo`.`patientcase` where id = #{id}")
    int deleteById(Patientcase patientcase);

    // select
    @Select("SELECT * FROM `docdemo`.`patientcase` where id = #{id}")
    Patientcase findPatientcaseById(Patientcase patientcase);

    @Select("SELECT * FROM `docdemo`.`patientcase` where id = #{id}")
    Patientcase findPatientcaseById1(Integer id);

    // 翻页
    @Select({ "<script>", "SELECT COUNT(*) FROM docdemo.patientcase N", "WHERE N.uuid LIKE CONCAT('%',#{uuid},'%')",
            "</script>" })
    int count(Patientcase patientcase);

    @Select({ "<script>", "SELECT * FROM docdemo.patientcase N ", "WHERE N.uuid LIKE CONCAT('%',#{uuid},'%')",
            "order by " + Constant.OrderByCreatetimeDesc + ",createtime desc", "limit #{start},#{end}", "</script>" })
    List<Patientcase> list(Patientcase patientcase);

    // 更新病人信息
    @Update("Update `docdemo`.`patientcase` SET `casename`=#{casename}, `uuid`=#{uuid},`occurancetime`=#{occurancetime},`maincase`=#{maincase},`casehis`=#{casehis},`diagnosis`=#{diagnosis},`suggestion`=#{suggestion},`remark`=#{remark},`createtime`=#{createtime} WHERE `id` = #{id};")
    int updateAll(Patientcase patientcase);
}
