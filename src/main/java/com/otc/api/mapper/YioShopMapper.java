package com.otc.api.mapper;

import java.util.List;

import com.otc.api.pojo.shop.Shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.otc.api.domain.YioShop;

@Mapper
public interface YioShopMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop WHERE id = #{id}")
	YioShop findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop")
	List<YioShop> findAll();

	@Select("SELECT count(id) FROM yio_shop")
	Integer count();

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT id,name FROM yio_shop WHERE id = #{id}")
	List<Shop> findId(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT id,name FROM yio_shop s,yio_shop_group g ,yio_sys_user u  WHERE g.shop_id = s.id and u.id = g.sys_user_id and u.id = #{userId}")
	List<Shop> findGroupShops(@Param("userId") Integer userId);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token"),@Result(property = "disable", column = "disable")})
	@Select("SELECT id,name,createAt,app_id,username,password,login_time,authority,token,disable  FROM yio_shop WHERE id = #{id}")
	List<YioShop> findIdBusiness(@Param("id") Integer id);
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token"),@Result(property = "disable", column = "disable")})
	@Select("SELECT id,name,createAt,app_id,username,password,login_time,authority,token,disable FROM yio_shop")
	List<YioShop> findBusiness();
	
	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT id,name FROM yio_shop")
	List<Shop> find();

	@Insert("insert into yio_shop (name,private_key,createAt,app_id,username,password,login_time,authority,token,disable) values (#{name},#{privateKey},#{createAt},#{appId},#{username},#{password},#{loginTime},#{authority},#{token},#{disable})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShop yioShop);

	@Update("update yio_shop set disable=#{disable} where id=#{id}")
	int update(YioShop yioShop);

	@Delete("delete from yio_shop where id=#{id}")
	int delete(YioShop yioShop);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT id FROM yio_shop where name = #{name}")
	List<YioShop> findAllByName(@Param("name") String name);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop where username = #{username} and password = #{password}")
	List<YioShop> findAllByUserNameAndPass(@Param("username") String username,@Param("password") String password);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop where token = #{token}")
	YioShop findAllByToken(@Param("token") String token);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id")})
	@Select("SELECT * FROM yio_shop WHERE app_id = #{appId}")
	YioShop findByAppId(@Param("appId") String appId);
}
