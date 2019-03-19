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

import com.otc.api.domain.YioShopGroup;

@Mapper
public interface YioShopGroupMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "shopId", column = "shop_id"),@Result(property = "sysUserId", column = "sys_user_id")})
	@Select("SELECT * FROM yio_shop_group WHERE id = #{id}")
	YioShopGroup findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "shopId", column = "shop_id"),@Result(property = "sysUserId", column = "sys_user_id")})
	@Select("SELECT * FROM yio_shop_group")
	List<YioShopGroup> findAll();

	@Insert("insert into yio_shop_group (shop_id,sys_user_id) values (#{shopId},#{sysUserId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShopGroup yioShopGroup);

	@Update("update yio_shop_group set shop_id=#{shopId},sys_user_id=#{sysUserId} where id=#{id}")
	int update(YioShopGroup yioShopGroup);

	@Delete("delete from yio_shop_group where id=#{id}")
	int delete(YioShopGroup yioShopGroup);


}
