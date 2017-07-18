package com.liumapp.DNSBee.dao;

import com.liumapp.DNSBee.model.ZonesFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface ZonesFileDAO {

    @Select("SELECT * FROM ZonesFile where type=1")
    public List<ZonesFile> findPublic();

    @Select("SELECT * FROM ZonesFile where user=#{user}")
    public List<ZonesFile> findByUser(@Param("user") String user);

    @Select("SELECT * FROM ZonesFile where id = #{id}")
    public ZonesFile load(@Param("id") int id);

    @Insert("insert into ZonesFile (`type`,`user`,`name`,`text`) values (#{type},#{user},#{name},#{text})")
    public int add(ZonesFile zonesFile);

    @Update("update ZonesFile set `name`=#{name},`text`=#{text} where `id`=#{id}")
    public int update(ZonesFile zonesFile);

    @Update("delete from ZonesFile where `id`=#{id}")
    public int deleteById(@Param("id") int id);

}
