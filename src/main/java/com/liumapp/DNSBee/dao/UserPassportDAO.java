package com.liumapp.DNSBee.dao;

import com.liumapp.DNSBee.model.UserPassport;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface UserPassportDAO {

    @Select("select * from User_Passport where username=#{username}")
    public UserPassport getByUsername(@Param("username") String username);

    @Select("select * from User_Passport where ticket=#{ticket}")
    public UserPassport getByTicket(@Param("ticket") String ticket);

    @Insert("insert into User_Passport (`userNumber`,`username`,`passwordSalt`,`salt`,`ticket`) values"+
            " (#{userNumber},#{username},#{passwordSalt},#{salt},#{ticket})")
    public int insert(UserPassport userPassport);

    @Update("update User_Passport set `userNumber`=#{userNumber},`username`=#{username},`passwordSalt`=#{passwordSalt},"+
            "`salt`=#{salt},`ticket`=#{ticket} where `id`=#{id}")
    public int update(UserPassport userPassport);

}
