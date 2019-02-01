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

import com.otc.api.domain.YioAccount;

@Mapper
public interface YioAccountMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "totalStream", column = "total_stream"),@Result(property = "createAt", column = "createAt"),@Result(property = "updateAt", column = "updateAt"),@Result(property = "version", column = "version"),@Result(property = "token", column = "token"),@Result(property = "frozen", column = "frozen")})
	@Select("SELECT * FROM yio_account WHERE id = #{id}")
	YioAccount findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "totalStream", column = "total_stream"),@Result(property = "createAt", column = "createAt"),@Result(property = "updateAt", column = "updateAt"),@Result(property = "version", column = "version"),@Result(property = "token", column = "token"),@Result(property = "frozen", column = "frozen")})
	@Select("SELECT * FROM yio_account")
	List<YioAccount> findAll();

	@Insert("insert into yio_account (amount,seller_id,user_id,total_stream,createAt,updateAt,version,token,frozen) values (#{amount},#{sellerId},#{userId},#{totalStream},#{createAt},#{updateAt},#{version},#{token},#{frozen})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioAccount yioAccount);

	@Update("update yio_account set amount=#{amount},seller_id=#{sellerId},user_id=#{userId},total_stream=#{totalStream},createAt=#{createAt},updateAt=#{updateAt},version=#{version},token=#{token},frozen=#{frozen} where id=#{id}")
	int update(YioAccount yioAccount);

	@Delete("delete from yio_account where id=#{id}")
	int delete(YioAccount yioAccount);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "totalStream", column = "total_stream"),@Result(property = "createAt", column = "createAt"),@Result(property = "updateAt", column = "updateAt"),@Result(property = "version", column = "version"),@Result(property = "token", column = "token"),@Result(property = "frozen", column = "frozen")})
	@Select("SELECT * FROM yio_account where user_id = #{userId}")
	List<YioAccount> findAllByUserId(@Param("userId") Integer userId);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "totalStream", column = "total_stream"),@Result(property = "createAt", column = "createAt"),@Result(property = "updateAt", column = "updateAt"),@Result(property = "version", column = "version"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_account WHERE seller_id = #{sellerId}")
	YioAccount findBySellerId(@Param("sellerId") Integer sellerId);
}
