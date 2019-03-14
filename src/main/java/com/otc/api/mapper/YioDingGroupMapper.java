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

import com.otc.api.domain.YioDingGroup;

@Mapper
public interface YioDingGroupMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "gourpName", column = "gourp_name")})
	@Select("SELECT * FROM yio_ding_group WHERE id = #{id}")
	YioDingGroup findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "gourpName", column = "gourp_name")})
	@Select("SELECT * FROM yio_ding_group")
	List<YioDingGroup> findAll();

	@Insert("insert into yio_ding_group (gourp_name) values (#{gourpName})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioDingGroup yioDingGroup);

	@Update("update yio_ding_group set gourp_name=#{gourpName} where id=#{id}")
	int update(YioDingGroup yioDingGroup);

	@Delete("delete from yio_ding_group where id=#{id}")
	int delete(YioDingGroup yioDingGroup);


}
