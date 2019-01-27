package com.otc.api.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.otc.api.pojo.detail.DetailPoJo;
import com.otc.api.pojo.detail.DetailServerPoJo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioAccountDetail;
import org.web3j.abi.datatypes.Int;

@Mapper
public interface YioAccountDetailMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "accountId", column = "account_id"),@Result(property = "amount", column = "amount"),@Result(property = "inOut", column = "in_out"),@Result(property = "serviceId", column = "service_id"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "createAt", column = "createAt")})
	@Select("SELECT * FROM yio_account_detail WHERE id = #{id}")
	YioAccountDetail findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "accountId", column = "account_id"),@Result(property = "amount", column = "amount"),@Result(property = "inOut", column = "in_out"),@Result(property = "serviceId", column = "service_id"),@Result(property = "userId", column = "user_id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "createAt", column = "createAt")})
	@Select("SELECT * FROM yio_account_detail")
	List<YioAccountDetail> findAll();

	@Insert("insert into yio_account_detail (account_id,amount,in_out,service_id,user_id,seller_id,createAt) values (#{accountId},#{amount},#{inOut},#{serviceId},#{userId},#{sellerId},#{createAt})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioAccountDetail yioAccountDetail);

	@Update("update yio_account_detail set account_id=#{accountId},amount=#{amount},in_out=#{inOut},service_id=#{serviceId},user_id=#{userId},seller_id=#{sellerId},createAt=#{createAt} where id=#{id}")
	int update(YioAccountDetail yioAccountDetail);

	@Delete("delete from yio_account_detail where id=#{id}")
	int delete(YioAccountDetail yioAccountDetail);

	@Select("<script>" +
			"select * from (SELECT o.id as id,d.createAt as createdAt,o.order_id as orderNo,o.extension as serverNo,d.in_out as type,d.amount,o.pay_qr as appId FROM yio_account_detail d,yio_orders o where d.in_out =1 and d.service_id=o.id " +
			" union all " +
			"SELECT w.id as id,d.createAt as createdAt,w.withdraw_no as orderNo,w.orderId as serverNo,d.in_out as type,d.amount as amount,w.app_id as appId FROM yio_account_detail d,yio_withdraw w where d.in_out =2 and d.service_id=w.id) as detail where detail.appId = #{appId}" +
			"<if test=\"start!=null\">"+
				"and detail.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
				"and detail.orderNo = #{orderNo}"+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
				"and detail.serverNo = #{serverNo}"+
			"</if>"+
			"<if test=\"type!=null\">"+
				"and detail.type = #{type}"+
			"</if>"+
				"order by detail.createdAt desc"+
			"</script>")
	List<DetailPoJo> query(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end,@Param("type") Integer type);

	@Select("<script>" +
			"select * from (SELECT o.id as id,d.createAt as createdAt,o.order_id as orderNo,o.extension as serverNo,d.in_out as type,d.amount,o.pay_qr as appId,s.username as username , '' as serverName FROM yio_account_detail d,yio_orders o,yio_seller s where d.in_out =1 and d.service_id=o.id and s.id = o.seller_id" +
			" union all " +
			"SELECT w.id as id,d.createAt as createdAt,w.withdraw_no as orderNo,w.orderId as serverNo,d.in_out as type,d.amount as amount,w.app_id as appId,s.username as username , w.name as serverName FROM yio_account_detail d,yio_withdraw w ,yio_seller s where d.in_out =2 and d.service_id=w.id and w.seller_id = s.id ) as detail where detail.appId = #{appId}" +
			"<if test=\"start!=null\">"+
				"and detail.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and detail.orderNo like \"%\"#{orderNo}\"%\""+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
			"and detail.serverNo like \"%\"#{serverNo}\"%\""+
			"</if>"+
			"<if test=\"type!=null\">"+
			"and detail.type = #{type}"+
			"</if>"+
			"order by detail.createdAt desc"+
			"</script>")
	List<DetailServerPoJo> queryByServer(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end, @Param("type") Integer type);

	@Select("<script>" +
			"SELECT sum(d.amount) FROM yio_account_detail d,yio_orders o where d.in_out =1 and d.service_id=o.id and o.pay_qr = #{appId}" +
			"<if test=\"start!=null\">"+
			"and d.createAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.order_id = #{orderNo}"+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
			"and o.extension = #{serverNo}"+
			"</if>"+
			"</script>")
	BigDecimal querySum(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end);

	@Select("<script>" +
			"SELECT count(d.id) FROM yio_account_detail d,yio_orders o where d.in_out =1 and d.service_id=o.id and o.pay_qr = #{appId}" +
			"<if test=\"start!=null\">"+
			"and d.createAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.order_id = #{orderNo}"+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
			"and o.extension = #{serverNo}"+
			"</if>"+
			"</script>")
	Integer queryCount(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end);

	@Select("<script>" +
			"SELECT sum(d.amount) FROM yio_account_detail d,yio_withdraw w where d.in_out =2 and d.service_id=w.id and w.app_id = #{appId}" +
			"<if test=\"start!=null\">"+
			"and d.createAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and w.withdraw_no = #{orderNo}"+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
			"and w.orderId = #{serverNo}"+
			"</if>"+
			"</script>")
	BigDecimal querySumServer(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end);

	@Select("<script>" +
			"SELECT count(d.id) FROM yio_account_detail d,yio_withdraw w where d.in_out =2 and d.service_id=w.id and w.app_id = #{appId}" +
			"<if test=\"start!=null\">"+
			"and d.createAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and w.withdraw_no = #{orderNo}"+
			"</if>"+
			"<if test=\"serverNo!=null and serverNo!=''\">"+
			"and w.orderId = #{serverNo}"+
			"</if>"+
			"</script>")
	Integer queryCountServer(@Param("appId") String appId, @Param("orderNo") String orderNo, @Param("serverNo") String serverNo, @Param("start") Date start, @Param("end") Date end);

}
