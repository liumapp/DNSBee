package com.liumapp.DNSBee.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface UserZonesDAO {

    @Update("update User_Passport set `zones`=#{zones} where `id`=#{userId}")
    public int updateZones(@Param("userId") int userId, @Param("zones") String zones);

    @Select("select zones from User_Passport where `id`=#{userId}")
    public String getZones(@Param("userId") int userId);

}

