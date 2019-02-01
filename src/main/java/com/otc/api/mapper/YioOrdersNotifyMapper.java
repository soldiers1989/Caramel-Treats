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

import com.otc.api.domain.YioOrdersNotify;

@Mapper
public interface YioOrdersNotifyMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "redirect", column = "redirect"),@Result(property = "type", column = "type"),@Result(property = "version", column = "version"),@Result(property = "redirectUrl", column = "redirect_url")})
	@Select("SELECT * FROM yio_orders_notify WHERE id = #{id}")
	YioOrdersNotify findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "redirect", column = "redirect"),@Result(property = "type", column = "type"),@Result(property = "version", column = "version"),@Result(property = "redirectUrl", column = "redirect_url")})
	@Select("SELECT * FROM yio_orders_notify")
	List<YioOrdersNotify> findAll();

	@Insert("insert into yio_orders_notify (redirect,type,version,redirect_url) values (#{redirect},#{type},#{version},#{redirectUrl})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioOrdersNotify yioOrdersNotify);

	@Update("update yio_orders_notify set redirect=#{redirect},type=#{type},version=#{version},redirect_url=#{redirectUrl} where id=#{id}")
	int update(YioOrdersNotify yioOrdersNotify);

	@Delete("delete from yio_orders_notify where id=#{id}")
	int delete(YioOrdersNotify yioOrdersNotify);


}
