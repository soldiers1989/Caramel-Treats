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

import com.otc.api.domain.YioRedLog;

@Mapper
public interface YioRedLogMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "orderNo", column = "order_no"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_red_log WHERE id = #{id}")
	YioRedLog findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "orderNo", column = "order_no"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_red_log")
	List<YioRedLog> findAll();

	@Insert("insert into yio_red_log (order_id,order_no,createdAt,type) values (#{orderId},#{orderNo},#{createdAt},#{type})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioRedLog yioRedLog);

	@Update("update yio_red_log set order_id=#{orderId},order_no=#{orderNo},createdAt=#{createdAt},type=#{type} where id=#{id}")
	int update(YioRedLog yioRedLog);

	@Delete("delete from yio_red_log where id=#{id}")
	int delete(YioRedLog yioRedLog);


}
