package com.otc.api.mapper;

import java.util.Date;
import java.util.List;

import com.otc.api.pojo.exception.ExceptionPoJo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioExceptionOrders;

@Mapper
public interface YioExceptionOrdersMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "amount", column = "amount")})
	@Select("SELECT * FROM yio_exception_orders WHERE id = #{id}")
	YioExceptionOrders findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "sellerId", column = "seller_id"),@Result(property = "userId", column = "user_id"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "amount", column = "amount")})
	@Select("SELECT * FROM yio_exception_orders")
	List<YioExceptionOrders> findAll();

	@Insert("insert into yio_exception_orders (seller_id,user_id,createdAt,amount) values (#{sellerId},#{userId},#{createdAt},#{amount})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioExceptionOrders yioExceptionOrders);

	@Update("update yio_exception_orders set seller_id=#{sellerId},user_id=#{userId},createdAt=#{createdAt},amount=#{amount} where id=#{id}")
	int update(YioExceptionOrders yioExceptionOrders);

	@Update("update yio_exception_orders set status=#{status} where id=#{id}")
	int updateStatus(YioExceptionOrders yioExceptionOrders);

	@Delete("delete from yio_exception_orders where id=#{id}")
	int delete(YioExceptionOrders yioExceptionOrders);

	@Select("<script>" +
			"SELECT e.id as id,e.amount as amount,u.username as username ,s.username as payName,e.createdAt as createdAt,s.name as name,s.type as type,e.status as status FROM yio_exception_orders e,yio_user u,yio_seller s where u.id = s.user_id and e.user_id = u.id and e.seller_id = s.id" +
			"<if test=\"start!=null\">"+
				"and e.createdAt between #{start} and #{end} " +
			"</if>" +
			"<if test=\"username!=null and username!=null\">"+
				"and u.username like \"%\"#{username}\"%\""+
			"</if>"+
			"<if test=\"status!=null\">"+
				"and e.status = #{status}"+
			"</if>"+
			" order by id desc"+
			"</script>")
	List<ExceptionPoJo> queryAllException(@Param("start") Date start,@Param("end") Date end,@Param("username") String username,@Param("status") Integer status);
}
