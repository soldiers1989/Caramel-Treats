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

import com.otc.api.domain.YioRatio;

@Mapper
public interface YioRatioMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "ratio", column = "ratio")})
	@Select("SELECT * FROM yio_ratio WHERE id = #{id}")
	YioRatio findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "ratio", column = "ratio")})
	@Select("SELECT * FROM yio_ratio")
	List<YioRatio> findAll();

	@Insert("insert into yio_ratio (ratio) values (#{ratio})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioRatio yioRatio);

	@Update("update yio_ratio set ratio=#{ratio} where id=#{id}")
	int update(YioRatio yioRatio);

	@Delete("delete from yio_ratio where id=#{id}")
	int delete(YioRatio yioRatio);


}
