package com.otc.api.mapper;

import java.util.List;

import com.otc.api.pojo.shop.Shop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

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
	@Select("SELECT id,name FROM yio_shop")
	List<Shop> find();

	@Insert("insert into yio_shop (name,private_key,createAt,app_id,username,password,login_time,authority,token) values (#{name},#{privateKey},#{createAt},#{appId},#{username},#{password},#{loginTime},#{authority},#{token})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioShop yioShop);

	@Update("update yio_shop set name=#{name},private_key=#{privateKey},createAt=#{createAt},app_id=#{appId},username=#{username},password=#{password},login_time=#{loginTime},authority=#{authority},token=#{token} where id=#{id}")
	int update(YioShop yioShop);

	@Delete("delete from yio_shop where id=#{id}")
	int delete(YioShop yioShop);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop where username = #{username}")
	List<YioShop> findAllByName(@Param("username") String username);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop where username = #{username} and password = #{password}")
	List<YioShop> findAllByUserNameAndPass(@Param("username") String username,@Param("password") String password);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_shop where token = #{token}")
	YioShop findAllByToken(@Param("token") String token);
}
