package com.medical.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.medical.domain.Admin;
import com.medical.domain.News;
import com.medical.domain.Patient;
import com.medical.util.Constant;


@Mapper
public interface PatientDao {

	// 添加病人信息
	@Insert("INSERT INTO `docdemo`.`patient` (`id`, `username`, `sex`, `birthday`, `age`, `phonenum`, `cid`, `wechatnum`, `origin`, `profession`,`address`,`height`,`weight`,`flood`,`menses`,`person`,`birthis`,`family`,`allergy`,`createtime`,`uuid`,`state`) VALUES (null, #{username}, #{sex}, #{birthday}, #{age}, #{phonenum}, #{cid}, #{wechatnum}, #{origin}, #{profession},#{address},#{height},#{weight},#{flood},#{menses},#{person},#{birthis},#{family},#{allergy},#{createtime},#{uuid},#{state});")
	int insert(Patient patient);
	// 按照 uuid 查询用户信息
	@Select("SELECT * FROM `docdemo`.`patient` where uuid = #{uuid};")
	Patient findPatientByUuid(String uuid);
	
	// 查询所有的用户
	@Select("SELECT * FROM `docdemo`.`patient`")
	List<Patient> findall();
	
	@Select("SELECT * FROM `docdemo`.`patient` where id = #{id}")
	Patient findUuidById(Patient patient);
	
	// 删除
	@Delete("DELETE FROM `docdemo`.`patient` where id = #{id}")
	int deleteById(Patient patient); 
	
	// 翻页
	
	@Select({
		"<script>",
		"SELECT COUNT(*) FROM docdemo.patient",
		"</script>"
	})
	int count(Patient patient);
	
	@Select({
		"<script>",
		"SELECT * FROM docdemo.patient N ",
		"WHERE N.username LIKE CONCAT('%',#{username},'%')",
		"order by "+Constant.OrderByCreatetimeDesc+",createtime desc",
			"limit #{start},#{end}",
		"</script>"
	})
	List<Patient> list(Patient patient);
	
	// 添加病人信息
	@Update("Update `docdemo`.`patient` SET `lastcase` = #{lastcase} WHERE `id` = #{id};")
	int updateLastcase(Patient patient);
	
	
	// 更新病人信息
		@Update("Update `docdemo`.`patient` SET `username`=#{username}, `age`=#{age},`birthday`=#{birthday},`phonenum`=#{phonenum},`cid`=#{cid},`wechatnum`=#{wechatnum},`origin`=#{origin},`profession`=#{profession},`address`=#{address},`height`=#{height},`weight`=#{weight},`flood`=#{flood},`menses`=#{menses},`person`=#{person},`birthis`=#{birthis},`family`=#{family},`allergy`=#{allergy},`sex` = #{sex},`createtime` = #{createtime},`state`=#{state},`lastcase` = #{lastcase} WHERE `uuid` = #{uuid};")
		int updateAll(Patient patient);
}
