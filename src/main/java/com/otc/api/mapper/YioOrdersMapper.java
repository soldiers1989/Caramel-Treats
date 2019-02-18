package com.otc.api.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.otc.api.pojo.index.IndexReport;
import com.otc.api.pojo.order.OrderList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioOrders;

@Mapper
public interface YioOrdersMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "orderPrice", column = "order_price"),@Result(property = "payPrice", column = "pay_price"),@Result(property = "payStatus", column = "pay_status"),@Result(property = "payType", column = "pay_type"),@Result(property = "payFormat", column = "pay_format"),@Result(property = "payQr", column = "pay_qr"),@Result(property = "redirectUrl", column = "redirect_url"),@Result(property = "extension", column = "extension"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "deletedAt", column = "deletedAt"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_orders WHERE id = #{id}")
	YioOrders findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "orderPrice", column = "order_price"),@Result(property = "payPrice", column = "pay_price"),@Result(property = "payStatus", column = "pay_status"),@Result(property = "payType", column = "pay_type"),@Result(property = "payFormat", column = "pay_format"),@Result(property = "payQr", column = "pay_qr"),@Result(property = "redirectUrl", column = "redirect_url"),@Result(property = "extension", column = "extension"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "deletedAt", column = "deletedAt"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_orders WHERE order_id = #{orderNo} and extension = #{serverNo}")
	YioOrders findByExtension(@Param("orderNo") String orderNo,@Param("serverNo") String serverNo);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "orderId", column = "order_id"),@Result(property = "orderPrice", column = "order_price"),@Result(property = "payPrice", column = "pay_price"),@Result(property = "payStatus", column = "pay_status"),@Result(property = "payType", column = "pay_type"),@Result(property = "payFormat", column = "pay_format"),@Result(property = "payQr", column = "pay_qr"),@Result(property = "redirectUrl", column = "redirect_url"),@Result(property = "extension", column = "extension"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "deletedAt", column = "deletedAt"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "type", column = "type")})
	@Select("SELECT * FROM yio_orders")
	List<YioOrders> findAll();

	@Insert("insert into yio_orders (order_id,order_price,pay_price,pay_status,pay_type,pay_format,pay_qr,redirect_url,extension,createdAt,updatedAt,deletedAt,seller_id,user_id,type) values (#{orderId},#{orderPrice},#{payPrice},#{payStatus},#{payType},#{payFormat},#{payQr},#{redirectUrl},#{extension},#{createdAt},#{updatedAt},#{deletedAt},#{sellerId},#{userId},#{type})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioOrders yioOrders);

	@Update("update yio_orders set order_id=#{orderId},order_price=#{orderPrice},pay_price=#{payPrice},pay_status=#{payStatus},pay_type=#{payType},pay_format=#{payFormat},pay_qr=#{payQr},redirect_url=#{redirectUrl},extension=#{extension},createdAt=#{createdAt},updatedAt=#{updatedAt},deletedAt=#{deletedAt},seller_id=#{sellerId},user_id=#{userId},type=#{type} where id=#{id}")
	int update(YioOrders yioOrders);

	@Delete("delete from yio_orders where id=#{id}")
	int delete(YioOrders yioOrders);

	@Select("SELECT sum(order_price) FROM yio_orders where date(createdAt) = date(#{date}) and pay_qr = #{appId} and pay_status ='已支付'")
	BigDecimal sumByDate(@Param("appId") String appId,@Param("date") Date date,@Param("status") String status);

	@Select("SELECT sum(order_price) FROM yio_orders where pay_qr = #{appId} and pay_status ='已支付'")
	BigDecimal sum(@Param("appId") String appId,@Param("status") String status);

	@Select("SELECT sum(order_price) FROM yio_orders where pay_qr = #{appId} and pay_type = #{payType} and pay_status ='已支付'")
	BigDecimal sumByPayType(@Param("appId") String appId,@Param("payType") String payType,@Param("status") String status);

	@Select("SELECT sum(order_price) as amount,date(createdAt) as date FROM yio_orders where pay_status ='已支付' and pay_qr = #{appId} group by date(createdAt) order by date desc limit #{limit}")
	List<IndexReport> report(@Param("appId") String appId,@Param("limit") Integer limit);

	@Select("SELECT count(id) FROM yio_orders WHERE date(createdAt) = date(#{date}) and pay_qr = #{appId} and pay_status ='已支付'")
	Integer countByCreateDate(@Param("appId") String appId,@Param("date") Date date);

	@Select("<script>" +
			"select sum(order_price) from yio_orders,yio_shop,yio_user where yio_orders.user_id=yio_user.id and app_id=extension and yio_orders.createdAt between #{date1} and #{date2}" +
			"<if test=\"name!=null and name!=''\">"+
			"and name=#{name}" +
			"</if>" +
			"<if test=\"minPrice!=null and maxPrice!=null\">"+
			"and order_price between #{minPrice} and #{maxPrice}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
			"and username=#{username}"+
			"</if>"+
			"</script>")
	BigDecimal findOrderPriceByDate(@Param("date1") Date date1, @Param("date2") Date date2 ,@Param("name") String name,@Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice,@Param("username")String username);

	@Select("SELECT count(order_price) FROM yio_orders WHERE createdAt between #{date1} and #{date2}")
	int findOrderCountByDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT sum(pay_price) FROM yio_orders WHERE createdAt between #{date1} and #{date2}")
	BigDecimal findPayPriceByDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT count(pay_price) FROM yio_orders WHERE createdAt between #{date1} and #{date2}")
	int findPayCountByDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT sum(order_price) FROM yio_orders WHERE createdAt between #{date1} and #{date2} and type=2")
	BigDecimal getPriceByDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT count(order_price) FROM yio_orders WHERE createdAt between #{date1} and #{date2} and type=2")
	int getCountByDate(@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT sum(pay_price) FROM yio_orders WHERE user_id = #{userId} and createdAt between #{date1} and #{date2} and pay_status ='已支付'")
	BigDecimal sumPayPrice(@Param("userId") Integer userId,@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("SELECT count(id) FROM yio_orders WHERE user_id = #{userId} and createdAt between #{date1} and #{date2} and pay_status ='已支付'")
	Integer countPayPrice(@Param("userId") Integer userId,@Param("date1") Date date1, @Param("date2") Date date2);

	@Select("<script>" +
			"select sum(order_price) from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId}" +
			"<if test=\"start!=null\">"+
				"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
				"and o.order_id = #{orderId}"+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
				"and o.extension=#{orderNo}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
				"and u.username=#{username}"+
			"</if>"+
			"</script>")
	BigDecimal querySumOrderPrice(@Param("appId") String appId,@Param("start") Date start,@Param("end") Date end,@Param("orderId") String orderId,@Param("orderNo") String orderNo,@Param("username") String username);

	@Select("<script>" +
			"select count(o.id) from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId} " +
			"<if test=\"start!=null\">"+
			"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
			"and o.order_id = #{orderId}"+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.extension=#{orderNo}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
			"and u.username=#{username}"+
			"</if>"+
			"</script>")
	Integer queryCountOrderPrice(@Param("appId") String appId,@Param("start") Date start,@Param("end") Date end,@Param("orderId") String orderId,@Param("orderNo") String orderNo,@Param("username") String username);

	@Select("<script>" +
			"select sum(order_price) from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId} and pay_status ='已支付'" +
			"<if test=\"start!=null\">"+
			"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
			"and o.order_id = #{orderId}"+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.extension=#{orderNo}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
			"and u.username=#{username}"+
			"</if>"+
			"</script>")
	BigDecimal querySumOrderPriceAndStatus(@Param("appId") String appId,@Param("start") Date start,@Param("end") Date end,@Param("orderId") String orderId,@Param("orderNo") String orderNo,@Param("username") String username);

	@Select("<script>" +
			"select count(o.id) from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId} and pay_status ='已支付'" +
			"<if test=\"start!=null\">"+
			"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
			"and o.order_id = #{orderId}"+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.extension=#{orderNo}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
			"and u.username=#{username}"+
			"</if>"+
			"</script>")
	Integer queryCountOrderPriceAndStatus(@Param("appId") String appId,@Param("start") Date start,@Param("end") Date end,@Param("orderId") String orderId,@Param("orderNo") String orderNo,@Param("username") String username);

	@Select("<script>" +
			"select sum(pay_price) from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId}" +
			"<if test=\"start!=null\">"+
			"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
			"and o.order_id = #{orderId}"+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
			"and o.extension=#{orderNo}"+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
			"and u.username=#{username}"+
			"</if>"+
			"</script>")
	BigDecimal querySumPayPrice(@Param("appId") String appId,@Param("start") Date start,@Param("end") Date end,@Param("orderId") String orderId,@Param("orderNo") String orderNo,@Param("username") String username);

	@Results({ @Result(property = "orderId", column = "order_id"),@Result(property = "orderPrice", column = "order_price"),@Result(property = "payPrice", column = "pay_price")})
	@Select("<script>" +
			"select o.*,u.username as username,o.pay_type as payType,(select qname from yio_seller s where s.id = o.seller_id limit 1) as qname ,(select name from yio_seller s where s.user_id = u.id limit 1) as name from yio_orders o,yio_user u where u.id = o.user_id and o.pay_qr=#{appId}" +
			"<if test=\"start!=null\">"+
				"and o.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"orderId!=null and orderId!=null\">"+
				"and o.order_id like \"%\"#{orderId}\"%\""+
			"</if>"+
			"<if test=\"orderNo!=null and orderNo!=''\">"+
				"and o.extension like \"%\"#{orderNo}\"%\""+
			"</if>"+
			"<if test=\"username!=null and username!=''\">"+
				"and u.username like \"%\"#{username}\"%\""+
			"</if>"+
			"<if test=\"type!=null\">"+
				"and o.type=#{type}"+
			"</if>"+
			" order by o.id desc"+
			"</script>")
	List<OrderList> query(@Param("type") Integer type,@Param("appId") String appId, @Param("start") Date start, @Param("end") Date end, @Param("orderId") String orderId, @Param("orderNo") String orderNo, @Param("username") String username);


}
