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

import com.otc.api.domain.YioSysSettle;

@Mapper
public interface YioSysSettleMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderNo", column = "order_no"),@Result(property = "name", column = "name"),@Result(property = "amount", column = "amount"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "bankName", column = "bank_name"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "status", column = "status"),@Result(property = "createTime", column = "create_time"),@Result(property = "userId", column = "user_id")})
	@Select("SELECT * FROM yio_sys_settle WHERE id = #{id}")
	YioSysSettle findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderNo", column = "order_no"),@Result(property = "name", column = "name"),@Result(property = "amount", column = "amount"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "bankName", column = "bank_name"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "status", column = "status"),@Result(property = "createTime", column = "create_time"),@Result(property = "userId", column = "user_id")})
	@Select("SELECT * FROM yio_sys_settle")
	List<YioSysSettle> findAll();

	@Insert("insert into yio_sys_settle (order_no,name,amount,bank_card,bank_name,bank_deposit,status,create_time,user_id) values (#{orderNo},#{name},#{amount},#{bankCard},#{bankName},#{bankDeposit},#{status},#{createTime},#{userId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSysSettle yioSysSettle);

	@Update("update yio_sys_settle set order_no=#{orderNo},name=#{name},amount=#{amount},bank_card=#{bankCard},bank_name=#{bankName},bank_deposit=#{bankDeposit},status=#{status},create_time=#{createTime},user_id=#{userId} where id=#{id}")
	int update(YioSysSettle yioSysSettle);

	@Delete("delete from yio_sys_settle where id=#{id}")
	int delete(YioSysSettle yioSysSettle);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderNo", column = "order_no"),@Result(property = "name", column = "name"),@Result(property = "amount", column = "amount"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "bankName", column = "bank_name"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "status", column = "status"),@Result(property = "createTime", column = "create_time"),@Result(property = "userId", column = "user_id")})
	@Select("<script>" +
			"SELECT * FROM yio_sys_settle where 1 =1 " +
			"<if test=\"status!=null\">"+
				"and status = #{status} " +
			"</if>" +
			"</script>")
	List<YioSysSettle> queryByStatus(@Param("status") Integer status);

	@Select("SELECT s.id as id, f.id as settleId,f.create_time as createTime ,s.order_no as orderNo,s.name as name,f.pay_amount as payAmount ,f.amount as amount ,s.bank_card as bankCard,s.bank_name as bankName ,s.bank_deposit as bankDeposit,s.status as status,f.file_url as fileUrl,f.in_file_url as inFileUrl FROM yio_sys_settle s,yio_sys_settle_file f where s.id = f.settle_id and f.settle_id = #{settleId} order by f.id desc")
	List<SettleShow> findAllDetail(@Param("settleId") Integer settleId);
}
