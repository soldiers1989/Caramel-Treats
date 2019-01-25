package com.otc.api.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioSysBank;

@Mapper
public interface YioSysBankMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "name", column = "name"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "status", column = "status")})
	@Select("SELECT * FROM yio_sys_bank WHERE id = #{id}")
	YioSysBank findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "name", column = "name"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "status", column = "status")})
	@Select("SELECT * FROM yio_sys_bank")
	List<YioSysBank> findAll();

	@Insert("insert into yio_sys_bank (bank_card,name,bank_deposit,status) values (#{bankCard},#{name},#{bankDeposit},#{status})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSysBank yioSysBank);

	@Update("update yio_sys_bank set bank_card=#{bankCard},name=#{name},bank_deposit=#{bankDeposit},status=#{status} where id=#{id}")
	int update(YioSysBank yioSysBank);

	@Delete("delete from yio_sys_bank where id=#{id}")
	int delete(YioSysBank yioSysBank);

	@Update("update yio_sys_bank set status=#{status}")
	int updateStatus(@Param("status") Integer status);
}
