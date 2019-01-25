package com.otc.api.mapper;

import java.util.List;

import com.otc.api.domain.YioShop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioSysUser;

@Mapper
public interface YioSysUserMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "token", column = "token"),@Result(property = "loginTime", column = "login_time"),@Result(property = "createTime", column = "create_time"),@Result(property = "authority", column = "authority"),@Result(property = "serverId", column = "server_id")})
	@Select("SELECT * FROM yio_sys_user WHERE id = #{id}")
	YioSysUser findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "token", column = "token"),@Result(property = "loginTime", column = "login_time"),@Result(property = "createTime", column = "create_time"),@Result(property = "authority", column = "authority"),@Result(property = "serverId", column = "server_id")})
	@Select("SELECT * FROM yio_sys_user")
	List<YioSysUser> findAll();

	@Insert("insert into yio_sys_user (username,password,token,login_time,create_time,authority,server_id) values (#{username},#{password},#{token},#{loginTime},#{createTime},#{authority},#{serverId})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioSysUser yioSysUser);

	@Update("update yio_sys_user set username=#{username},password=#{password},token=#{token},login_time=#{loginTime},create_time=#{createTime},authority=#{authority},server_id=#{serverId} where id=#{id}")
	int update(YioSysUser yioSysUser);

	@Delete("delete from yio_sys_user where id=#{id}")
	int delete(YioSysUser yioSysUser);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_sys_user where username = #{username}")
	List<YioSysUser> findAllByName(@Param("username") String username);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_sys_user where username = #{username} and password = #{password}")
	List<YioSysUser> findAllByUserNameAndPass(@Param("username") String username,@Param("password") String password);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "name", column = "name"),@Result(property = "privateKey", column = "private_key"),@Result(property = "createAt", column = "createAt"),@Result(property = "appId", column = "app_id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "loginTime", column = "login_time"),@Result(property = "authority", column = "authority"),@Result(property = "token", column = "token")})
	@Select("SELECT * FROM yio_sys_user where token = #{token}")
	YioSysUser findAllByToken(@Param("token") String token);
}
