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

import com.otc.api.domain.YioPant;

@Mapper
public interface YioPantMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "timestamp", column = "timestamp"),@Result(property = "pant", column = "pant"),@Result(property = "type", column = "type"),@Result(property = "userId", column = "user_id")})
	@Select("SELECT * FROM yio_pant WHERE id = #{id}")
	YioPant findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "timestamp", column = "timestamp"),@Result(property = "pant", column = "pant"),@Result(property = "type", column = "type"),@Result(property = "userId", column = "user_id")})
	@Select("SELECT * FROM yio_pant")
	List<YioPant> findAll();

	@Insert("insert into yio_pant (seller_id,timestamp,pant,type,user_id) values (#{sellerId},#{timestamp},#{pant},#{type},#{userId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioPant yioPant);

	@Update("update yio_pant set seller_id=#{sellerId},timestamp=#{timestamp},pant=#{pant},type=#{type},user_id=#{userId} where id=#{id}")
	int update(YioPant yioPant);

	@Delete("delete from yio_pant where id=#{id}")
	int delete(YioPant yioPant);

	@Select("SELECT count(id) FROM yio_pant")
	Integer count();

	@Select("SELECT count(id) FROM yio_pant where pant = #{pant}")
	Integer countByPant(@Param("pant") Integer pant);
}
