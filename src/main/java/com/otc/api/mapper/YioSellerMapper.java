package com.otc.api.mapper;

import java.util.List;

import com.otc.api.pojo.seller.SellerList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioSeller;

@Mapper
public interface YioSellerMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "url", column = "url"),@Result(property = "type", column = "type"),@Result(property = "userId", column = "user_id"),@Result(property = "createAt", column = "createAt"),@Result(property = "username", column = "username"),@Result(property = "qr", column = "qr")})
	@Select("SELECT * FROM yio_seller WHERE id = #{id}")
	YioSeller findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "url", column = "url"),@Result(property = "type", column = "type"),@Result(property = "userId", column = "user_id"),@Result(property = "createAt", column = "createAt"),@Result(property = "username", column = "username"),@Result(property = "qr", column = "qr")})
	@Select("SELECT * FROM yio_seller")
	List<YioSeller> findAll();

	@Select("SELECT count(id) FROM yio_seller where type = #{type}")
	Integer countByType(@Param("type") Integer type);

	@Insert("insert into yio_seller (name,url,type,user_id,createAt,username,qr) values (#{name},#{url},#{type},#{userId},#{createAt},#{username},#{qr})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSeller yioSeller);

	@Update("update yio_seller set name=#{name},url=#{url},type=#{type},user_id=#{userId},createAt=#{createAt},username=#{username},qr=#{qr} where id=#{id}")
	int update(YioSeller yioSeller);

	@Delete("delete from yio_seller where id=#{id}")
	int delete(YioSeller yioSeller);

	@Select("SELECT id,username,name,type,qname,card_no as cardNo,(select bank_name from yio_bank yb where yb.id = s.bank_id) as bankName,(select amount from yio_account where seller_id = s.id) as amount, (select pant from yio_pant where seller_id = s.id) as pant FROM yio_seller s where s.user_id = #{userId}")
	List<SellerList> findAllByUserId(@Param("userId") Integer userId);
}
