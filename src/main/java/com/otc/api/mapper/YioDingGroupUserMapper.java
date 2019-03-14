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

import com.otc.api.domain.YioDingGroupUser;

@Mapper
public interface YioDingGroupUserMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "dingGroupId", column = "ding_group_id")})
	@Select("SELECT * FROM yio_ding_group_user WHERE id = #{id}")
	YioDingGroupUser findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "dingGroupId", column = "ding_group_id")})
	@Select("SELECT * FROM yio_ding_group_user")
	List<YioDingGroupUser> findAll();

	@Insert("insert into yio_ding_group_user (seller_id,ding_group_id) values (#{sellerId},#{dingGroupId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioDingGroupUser yioDingGroupUser);

	@Update("update yio_ding_group_user set seller_id=#{sellerId},ding_group_id=#{dingGroupId} where id=#{id}")
	int update(YioDingGroupUser yioDingGroupUser);

	@Delete("delete from yio_ding_group_user where id=#{id}")
	int delete(YioDingGroupUser yioDingGroupUser);


}
