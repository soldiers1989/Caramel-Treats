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

import com.otc.api.domain.YioShopEnsure;

@Mapper
public interface YioShopEnsureMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "shopId", column = "shop_id"),@Result(property = "amount", column = "amount"),@Result(property = "rate", column = "rate"),@Result(property = "createTime", column = "create_time"),@Result(property = "status", column = "status")})
	@Select("SELECT * FROM yio_shop_ensure WHERE id = #{id}")
	YioShopEnsure findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "shopId", column = "shop_id"),@Result(property = "amount", column = "amount"),@Result(property = "rate", column = "rate"),@Result(property = "createTime", column = "create_time"),@Result(property = "status", column = "status")})
	@Select("SELECT * FROM yio_shop_ensure")
	List<YioShopEnsure> findAll();

	@Insert("insert into yio_shop_ensure (shop_id,amount,rate,create_time,status) values (#{shopId},#{amount},#{rate},#{createTime},#{status})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShopEnsure yioShopEnsure);

	@Update("update yio_shop_ensure set shop_id=#{shopId},amount=#{amount},rate=#{rate},create_time=#{createTime},status=#{status} where id=#{id}")
	int update(YioShopEnsure yioShopEnsure);

	@Delete("delete from yio_shop_ensure where id=#{id}")
	int delete(YioShopEnsure yioShopEnsure);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "shopId", column = "shop_id"),@Result(property = "amount", column = "amount"),@Result(property = "rate", column = "rate"),@Result(property = "createTime", column = "create_time"),@Result(property = "status", column = "status")})
	@Select("SELECT * FROM yio_shop_ensure WHERE shop_id = #{shopId}")
	YioShopEnsure findByShopId(@Param("shopId") Integer shopId);
}
