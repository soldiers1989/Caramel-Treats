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

import com.otc.api.domain.YioBill;

@Mapper
public interface YioBillMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "stream", column = "stream"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "reward", column = "reward"),@Result(property = "createAt", column = "createAt"),@Result(property = "createDate", column = "create_date")})
	@Select("SELECT * FROM yio_bill WHERE id = #{id}")
	YioBill findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "stream", column = "stream"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "reward", column = "reward"),@Result(property = "createAt", column = "createAt"),@Result(property = "createDate", column = "create_date")})
	@Select("SELECT * FROM yio_bill")
	List<YioBill> findAll();

	@Insert("insert into yio_bill (stream,user_id,seller_id,reward,createAt,create_date) values (#{stream},#{userId},#{sellerId},#{reward},#{createAt},#{createDate})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioBill yioBill);

	@Update("update yio_bill set stream=#{stream},user_id=#{userId},seller_id=#{sellerId},reward=#{reward},createAt=#{createAt},create_date=#{createDate} where id=#{id}")
	int update(YioBill yioBill);

	@Delete("delete from yio_bill where id=#{id}")
	int delete(YioBill yioBill);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "stream", column = "stream"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "reward", column = "reward"),@Result(property = "createAt", column = "createAt"),@Result(property = "createDate", column = "create_date")})
	@Select("SELECT * FROM yio_bill WHERE seller_id = #{sellerId} and create_date=#{createDate}")
	YioBill findBySellerAndDate(@Param("sellerId") Integer sellerId,@Param("createDate") String createDate);
}
