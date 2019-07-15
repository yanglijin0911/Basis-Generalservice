package com.adb.adblab.generalservice.mapper;

import com.adb.adblab.generalservice.entity.AllCityShopNumForTheBrand;
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


    @Select("SELECT cityname from street_shop GROUP BY cityname")
    List<String> selectAllCity();

    @RequestMapping("com.adb.adblab.generalservice.entity.AllCityShopNumForTheBrand")
    @Select("SELECT t.brand_name as brandName,t.cityname as cityName, count(t.cityname) as number from (select brand_name , cityname from street_shop where pname like '%${pname}%' and brand_name like '%${brandName}%') t GROUP BY t.brand_name,t.cityname")
    List<AllCityShopNumForTheBrand> selectByPnameAndBrandName(@Param("pname") String pname, @Param("brandName") String brandName);


    @RequestMapping("com.adb.adblab.generalservice.entity.AllCityShopNumForTheBrand")
    @Select("SELECT t.brand_name as brandName,t.adname as cityName, count(t.adname) as number from (select brand_name , adname from street_shop where pname like '%${pname}%' and brand_name like '%${brandName}%') t GROUP BY t.brand_name,t.adname")
    List<AllCityShopNumForTheBrand> selectByPnameAndBrandName2(@Param("pname") String pname, @Param("brandName") String brandName);
}