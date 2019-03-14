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

import com.otc.api.domain.YioPayType;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;

@Mapper
public interface YioShopRateMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id")})
	@Select("SELECT * FROM yio_shop_rate WHERE id = #{id}")
	YioShopRate findById(@Param("id") Integer id);
	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "name", column = "name"),@Result(property = "parentId", column = "parent_id")})
	@Select("SELECT * FROM yio_pay_type ")
	List<YioPayType> findAllPayType();

	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id")})
	@Select("SELECT * FROM yio_shop_rate")
	List<YioShopRate> findAll();

	@Insert("insert into yio_shop_rate (pay_type,rate,shop_id) values (#{payType},#{rate},#{shopId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShopRate yioShopRate);

	@Update("update yio_shop_rate set pay_type=#{payType},rate=#{rate},shop_id=#{shopId} where id=#{id}")
	int update(YioShopRate yioShopRate);
	
	@Select("SELECT count(id) FROM yio_shop_rate where shop_id = #{shopId} ")
	int countRate(@Param("shopId") Integer shopId);
	
	@Update("update yio_shop_rate set  disable=#{disable} where id=#{id}")
	int updateOpenOrClose(@Param("id") Integer id,@Param("disable") Integer disable);
	
	@Update("update yio_shop_rate set disable=#{disable} where shop_id=#{shopId}")
	int closeOpenOrClose(@Param("disable") Integer disable ,@Param("shopId") Integer shopId);

	@Delete("delete from yio_shop_rate where id=#{id}")
	int delete(YioShopRate yioShopRate);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id")})
	@Select("SELECT * FROM yio_shop_rate where shop_id = #{shopId} and pay_type = #{payType}")
	YioShopRate findAllByPayType(@Param("shopId") Integer shopId,@Param("payType") Integer payType);
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id"),@Result(property = "disable", column = "disable")})
	@Select("SELECT * FROM yio_shop_rate where shop_id = #{shopId} ")
	List<YioShopRate> findAllByPayShopId(@Param("shopId") Integer shopId);
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id"),@Result(property = "disable", column = "disable")})
	@Select("SELECT id,pay_type,rate,shop_id,disable FROM yio_shop_rate where shop_id = #{shopId}")
	List<YioShopRate> findAllByShopId(@Param("shopId") Integer shopId);
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "rate", column = "rate"),@Result(property = "shopId", column = "shop_id")})
	@Select("SELECT * FROM yio_shop_rate where shop_id = #{shopId} and pay_type = #{payType}")
	List<YioShopRate> findAllByShopIdAndPayType(@Param("shopId") Integer shopId,@Param("payType") Integer payType);
}
