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

import com.otc.api.domain.YioQr;

@Mapper
public interface YioQrMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "status", column = "status"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_qr WHERE id = #{id}")
	YioQr findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "status", column = "status"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_qr")
	List<YioQr> findAll();

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "status", column = "status"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_qr where user_id = #{userId} and status = #{status}")
	List<YioQr> findAllByStatusAndUserId(@Param("userId") Integer userId,@Param("status") Integer status);

	@Insert("insert into yio_qr (amount,seller_id,user_id,status,file_url,type) values (#{amount},#{sellerId},#{userId},#{status},#{fileUrl},#{type})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioQr yioQr);

	@Update("update yio_qr set amount=#{amount},seller_id=#{sellerId},user_id=#{userId},status=#{status},file_url=#{fileUrl},type=#{type} where id=#{id}")
	int update(YioQr yioQr);

	@Update("update yio_qr set status=#{status} where user_id=#{id}")
	int updateStatus(@Param("id") Integer id,@Param("status") Integer status);

	@Delete("delete from yio_qr where id=#{id}")
	int delete(YioQr yioQr);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "status", column = "status"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_qr where user_id = #{userId} order by amount asc")
	List<YioQr> findAllByUserId(@Param("userId") Integer userId);
}
