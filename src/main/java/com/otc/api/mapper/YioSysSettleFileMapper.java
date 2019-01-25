package com.otc.api.mapper;

import java.util.List;

import com.otc.api.pojo.settle.SettleShow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioSysSettleFile;

@Mapper
public interface YioSysSettleFileMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "remark", column = "remark"),@Result(property = "payAmount", column = "pay_amount"),@Result(property = "amount", column = "amount"),@Result(property = "createTime", column = "create_time"),@Result(property = "settleId", column = "settle_id"),@Result(property = "inFileUrl", column = "in_file_url")})
	@Select("SELECT * FROM yio_sys_settle_file WHERE id = #{id}")
	YioSysSettleFile findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "fileUrl", column = "file_url"),@Result(property = "remark", column = "remark"),@Result(property = "payAmount", column = "pay_amount"),@Result(property = "amount", column = "amount"),@Result(property = "createTime", column = "create_time"),@Result(property = "settleId", column = "settle_id"),@Result(property = "inFileUrl", column = "in_file_url")})
	@Select("SELECT * FROM yio_sys_settle_file")
	List<YioSysSettleFile> findAll();

	@Insert("insert into yio_sys_settle_file (file_url,remark,pay_amount,amount,create_time,settle_id,in_file_url) values (#{fileUrl},#{remark},#{payAmount},#{amount},#{createTime},#{settleId},#{inFileUrl})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSysSettleFile yioSysSettleFile);

	@Update("update yio_sys_settle_file set file_url=#{fileUrl},remark=#{remark},pay_amount=#{payAmount},amount=#{amount},create_time=#{createTime},settle_id=#{settleId},in_file_url=#{inFileUrl} where id=#{id}")
	int update(YioSysSettleFile yioSysSettleFile);

	@Delete("delete from yio_sys_settle_file where id=#{id}")
	int delete(YioSysSettleFile yioSysSettleFile);

}
