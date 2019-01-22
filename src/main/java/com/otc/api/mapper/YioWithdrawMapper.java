package com.otc.api.mapper;

import java.math.BigDecimal;
import java.util.Date;
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

import com.otc.api.domain.YioWithdraw;

@Mapper
public interface YioWithdrawMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "appId", column = "app_id"),@Result(property = "amount", column = "amount"),@Result(property = "orderId", column = "orderId"),@Result(property = "payType", column = "pay_type"),@Result(property = "timestamp", column = "timestamp"),@Result(property = "name", column = "name"),@Result(property = "url", column = "url"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "notifyUrl", column = "notify_url"),@Result(property = "withdrawNo", column = "withdraw_no"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "payStatus", column = "pay_status"),@Result(property = "fileUrl", column = "file_url")})
	@Select("SELECT * FROM yio_withdraw WHERE id = #{id}")
	YioWithdraw findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "appId", column = "app_id"),@Result(property = "amount", column = "amount"),@Result(property = "orderId", column = "orderId"),@Result(property = "payType", column = "pay_type"),@Result(property = "timestamp", column = "timestamp"),@Result(property = "name", column = "name"),@Result(property = "url", column = "url"),@Result(property = "bankCard", column = "bank_card"),@Result(property = "bankDeposit", column = "bank_deposit"),@Result(property = "notifyUrl", column = "notify_url"),@Result(property = "withdrawNo", column = "withdraw_no"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "payStatus", column = "pay_status"),@Result(property = "fileUrl", column = "file_url")})
	@Select("SELECT * FROM yio_withdraw")
	List<YioWithdraw> findAll();

	@Insert("insert into yio_withdraw (app_id,amount,orderId,pay_type,timestamp,name,url,bank_card,bank_deposit,notify_url,withdraw_no,createdAt,user_id,seller_id,updatedAt,pay_status,file_url) values (#{appId},#{amount},#{orderId},#{payType},#{timestamp},#{name},#{url},#{bankCard},#{bankDeposit},#{notifyUrl},#{withdrawNo},#{createdAt},#{userId},#{sellerId},#{updatedAt},#{payStatus},#{fileUrl})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioWithdraw yioWithdraw);

	@Update("update yio_withdraw set app_id=#{appId},amount=#{amount},orderId=#{orderId},pay_type=#{payType},timestamp=#{timestamp},name=#{name},url=#{url},bank_card=#{bankCard},bank_deposit=#{bankDeposit},notify_url=#{notifyUrl},withdraw_no=#{withdrawNo},createdAt=#{createdAt},user_id=#{userId},seller_id=#{sellerId},updatedAt=#{updatedAt},pay_status=#{payStatus},file_url=#{fileUrl} where id=#{id}")
	int update(YioWithdraw yioWithdraw);

	@Delete("delete from yio_withdraw where id=#{id}")
	int delete(YioWithdraw yioWithdraw);

	@Select("SELECT sum(amount) FROM yio_withdraw where app_id =#{appId} and pay_status = #{payStatus}")
	BigDecimal sumByStatus(@Param("appId") String appId, @Param("payStatus") Integer payStatus);

	@Select("SELECT count(id) FROM yio_withdraw where app_id =#{appId} and pay_status = #{payStatus}")
	Integer countByStatus(@Param("appId") String appId, @Param("payStatus") Integer payStatus);

	@Select("SELECT sum(amount) FROM yio_withdraw where app_id =#{appId} and pay_status = #{payStatus} and date(createdAt) = date(#{date})")
	BigDecimal sumByStatusAndDate(@Param("appId") String appId, @Param("payStatus") Integer payStatus,@Param("date") Date date);

	@Select("SELECT count(id) FROM yio_withdraw where app_id =#{appId} and pay_status = #{payStatus} and date(createdAt) = date(#{date})")
	Integer countByStatusAndDate(@Param("appId") String appId, @Param("payStatus") Integer payStatus,@Param("date") Date date);
}
