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

import com.otc.api.domain.YioSelf;

@Mapper
public interface YioSelfMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "traderName", column = "trader_name"),@Result(property = "traderAccno", column = "trader_accno"),@Result(property = "selfCheckStatus", column = "self_check_status"),@Result(property = "accountStatus", column = "account_status")})
	@Select("SELECT * FROM yio_self WHERE id = #{id}")
	YioSelf findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "payType", column = "pay_type"),@Result(property = "traderName", column = "trader_name"),@Result(property = "traderAccno", column = "trader_accno"),@Result(property = "selfCheckStatus", column = "self_check_status"),@Result(property = "accountStatus", column = "account_status")})
	@Select("SELECT * FROM yio_self")
	List<YioSelf> findAll();
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "payType", column = "pay_type"),@Result(property = "createDate", column = "createDate"),@Result(property = "traderName", column = "trader_name"),@Result(property = "traderAccno", column = "trader_accno"),@Result(property = "selfCheckStatus", column = "self_check_status"),@Result(property = "accountStatus", column = "account_status")})
	@Select("<script>" +
			"select id,pay_type,trader_name,seller_id,order_id,trader_accno,self_check_status,account_status from yio_self o where o.self_check_status != 2" +
			"<if test=\"payType!=null\">"+
				"and o.pay_type = #{payType} " +
			"</if>" +
			"<if test=\"checkStatus!=null \">"+
				"and o.self_check_status = #{checkStatus}"+
			"</if>"+
			"<if test=\"qname!=null and qname!=''\">"+
				"and o.trader_name like \"%\"#{qname}\"%\"" +
			"</if>"+
			"<if test=\"transactionAccount!=null and transactionAccount!=''\">"+
				"and o.trader_accno like \"%\"#{transactionAccount}\"%\"" +
			"</if>"+
			
			"</script>")
	List<YioSelf> queryForList(@Param("payType")Integer payType,@Param("checkStatus")Integer checkStatus,@Param("qname")String qname,@Param("transactionAccount")String transactionAccount);
	
	@Insert("insert into yio_self (pay_type,trader_name,trader_accno,seller_id,self_check_status,account_status,createDate) values (#{payType},#{traderName},#{traderAccno},#{sellerId},#{selfCheckStatus},#{accountStatus},#{createDate})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSelf yioSelf);

	@Update("update yio_self set pay_type=#{payType},trader_name=#{traderName},trader_accno=#{traderAccno},self_check_status=#{selfCheckStatus},account_status=#{accountStatus} where id=#{id}")
	int update(YioSelf yioSelf);

	

	@Update("update yio_self set self_check_status=#{accountStatus} where id=#{id}")
	int updateOpenOrClose(@Param("id")Integer id,@Param("accountStatus") Integer accountStatus);
	
	@Update("update yio_self set self_check_status=#{accountStatus} where order_id=#{orderId}")
	int updateCheckStatusByOrder(@Param("orderId")String orderId,@Param("accountStatus") Integer accountStatus);
	
	@Update("update yio_self set order_id=#{orderId} where id=#{id}")
	int updateOrder(@Param("id")Integer id,@Param("orderId") String orderId);
	
	@Update("update yio_self set account_status=#{accountStatus} where id=#{id}")
	int updateAccountStatus(@Param("id")Integer id,@Param("accountStatus") Integer accountStatus);
	
	@Delete("delete from yio_self where id=#{id}")
	int delete(YioSelf yioSelf);
	
	
	@Results({ @Result(property = "sellerId", column = "id"),@Result(property = "payType", column = "type"),@Result(property = "traderName", column = "name"),@Result(property = "traderAccno", column = "username")})
	@Select("SELECT s.id,s.type,s.name,a.username FROM yio_seller s left join yio_user a on s.user_id = a.id")
	List<YioSelf> finds();

}
