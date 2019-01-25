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

import com.otc.api.domain.YioFile;

@Mapper
public interface YioFileMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "fileUrl", column = "file_url")})
	@Select("SELECT * FROM yio_file WHERE id = #{id}")
	YioFile findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "fileUrl", column = "file_url")})
	@Select("SELECT * FROM yio_file")
	List<YioFile> findAll();

	@Insert("insert into yio_file (name,file_url) values (#{name},#{fileUrl})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioFile yioFile);

	@Update("update yio_file set name=#{name},file_url=#{fileUrl} where id=#{id}")
	int update(YioFile yioFile);

	@Delete("delete from yio_file where id=#{id}")
	int delete(YioFile yioFile);


}
