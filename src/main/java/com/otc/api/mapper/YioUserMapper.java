package com.otc.api.mapper;

import java.util.List;

import com.otc.api.pojo.user.UserDetail;
import com.otc.api.pojo.user.UserList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.otc.api.domain.YioUser;

@Mapper
public interface YioUserMapper {

	@Results({ @Result(property = "id", column = "id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "lastLogin", column = "last_login"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "token", column = "token"),@Result(property = "phone", column = "phone"),@Result(property = "tradingPwd", column = "tradingPwd"),@Result(property = "authority", column = "authority"),@Result(property = "work", column = "work"),@Result(property = "invite", column = "invite"),@Result(property = "inviter", column = "inviter")})
	@Select("SELECT * FROM yio_user WHERE id = #{id}")
	YioUser findById(@Param("id") Integer id);

	@Results({ @Result(property = "id", column = "id"),@Result(property = "username", column = "username"),@Result(property = "password", column = "password"),@Result(property = "lastLogin", column = "last_login"),@Result(property = "createdAt", column = "createdAt"),@Result(property = "updatedAt", column = "updatedAt"),@Result(property = "token", column = "token"),@Result(property = "phone", column = "phone"),@Result(property = "tradingPwd", column = "tradingPwd"),@Result(property = "authority", column = "authority"),@Result(property = "work", column = "work"),@Result(property = "invite", column = "invite"),@Result(property = "inviter", column = "inviter")})
	@Select("SELECT * FROM yio_user")
	List<YioUser> findAll();

	@Select("SELECT count(id) FROM yio_user")
	Integer count();

	@Select("SELECT count(id) FROM yio_user where work = 1")
	Integer countWork();

	@Insert("insert into yio_user (username,password,last_login,createdAt,updatedAt,token,phone,tradingPwd,authority,work,invite,inviter) values (#{username},#{password},#{lastLogin},#{createdAt},#{updatedAt},#{token},#{phone},#{tradingPwd},#{authority},#{work},#{invite},#{inviter})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
	int insert(YioUser yioUser);

	@Update("update yio_user set username=#{username},password=#{password},last_login=#{lastLogin},createdAt=#{createdAt},updatedAt=#{updatedAt},token=#{token},phone=#{phone},tradingPwd=#{tradingPwd},authority=#{authority},work=#{work},invite=#{invite},inviter=#{inviter} where id=#{id}")
	int update(YioUser yioUser);

	@Update("update yio_user set status=#{status},work = #{work} where id=#{id}")
	int updateStatus(YioUser yioUser);

	@Delete("delete from yio_user where id=#{id}")
	int delete(YioUser yioUser);

	@Select("<script>" +
			"SELECT id,username,(select sum(amount) from yio_account where user_id = u.id) as amount,(SELECT sum(stream) FROM yio_bill where user_id = u.id) as reward,work,status FROM yio_user u WHERE 1=1" +
			"<if test=\"username!=null and username!=''\">"+
				"and username like \"%\"#{username}\"%\"" +
			"</if>" +
			"<if test=\"work!=null\">"+
				"and work = #{work}"+
			"</if>"+
			"</script>")
	List<UserList> query(@Param("username") String username,@Param("work") Integer wrok);


	@Select("SELECT id,username,(select sum(amount) from yio_account where user_id = u.id) as amount,(SELECT sum(stream) FROM yio_bill where user_id = u.id) as reward,work,status,(select sum(amount) from yio_withdraw where user_id =u.id and pay_status = 1) as withdraw FROM yio_user u WHERE u.id=#{id}")
	UserDetail findId(@Param("id") Integer id);


}
