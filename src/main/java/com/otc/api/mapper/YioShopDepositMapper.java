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

import com.otc.api.domain.YioShopDeposit;

@Mapper
public interface YioShopDepositMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "shopId", column = "shop_id"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt")})
	@Select("SELECT * FROM yio_shop_deposit WHERE id = #{id}")
	YioShopDeposit findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "shopId", column = "shop_id"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt")})
	@Select("SELECT * FROM yio_shop_deposit")
	List<YioShopDeposit> findAll();

	@Insert("insert into yio_shop_deposit (amount,shop_id,createdAt,updatedAt) values (#{amount},#{shopId},#{createdAt},#{updatedAt})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShopDeposit yioShopDeposit);

	@Update("update yio_shop_deposit set amount=#{amount},shop_id=#{shopId},createdAt=#{createdAt},updatedAt=#{updatedAt} where id=#{id}")
	int update(YioShopDeposit yioShopDeposit);

	@Delete("delete from yio_shop_deposit where id=#{id}")
	int delete(YioShopDeposit yioShopDeposit);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "amount", column = "amount"),@Result(property = "shopId", column = "shop_id"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt")})
	@Select("SELECT * FROM yio_shop_deposit where shop_id = #{shopId} for update")
	YioShopDeposit findAllByShopId(@Param("shopId") Integer shopId);
}
