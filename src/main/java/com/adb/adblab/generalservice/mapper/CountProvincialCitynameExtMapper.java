package com.adb.adblab.generalservice.mapper;

import com.adb.adblab.generalservice.entity.CityShopNumWithCityCode;
import com.adb.adblab.generalservice.entity.CountProvincialCityname;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CountProvincialCitynameExtMapper extends Mapper<CountProvincialCityname> {

    @Select("SELECT t1.pname as pname,t1.cityname as cityname,t1.number as number,t2.city_code as cityCode from count_provincial_cityname t1 JOIN city_code t2 on t1.pname = t2.pname and t1.cityname = t2.cityname where t1.pname like '${pname}%'")
    List<CityShopNumWithCityCode> selectCityShopNumWithCityCodeByProvincial(@Param("pname") String pname);
}