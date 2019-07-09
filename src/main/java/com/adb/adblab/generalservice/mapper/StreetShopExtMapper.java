package com.adb.adblab.generalservice.mapper;

import com.adb.adblab.generalservice.entity.CountStreetShop;
import com.adb.adblab.generalservice.entity.StreetShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StreetShopExtMapper extends Mapper<StreetShop> {

    @RequestMapping("com.adb.adblab.generalservice.entity.CountStreetShop")
    @Select("select pname,cityname,adname,street_address as streetAddress,count(street_address) as number from street_shop GROUP BY pname,cityname,adname,street_address")
    List<CountStreetShop> selectStreetShopByGroup();

    @RequestMapping("com.adb.adblab.generalservice.entity.StreetShop")
    @Select("select * from street_shop where pname =#{pname} and cityname=#{cityname} and adname=#{adname} and street_address = #{saddress}")
    List<StreetShop> selectByPnameAndCityname(@Param("pname") String pname,@Param("cityname") String cityname,
                                              @Param("adname") String adname,@Param("saddress") String saddress);
}