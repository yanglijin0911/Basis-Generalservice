package com.adb.adblab.generalservice.mapper;

import com.adb.adblab.generalservice.entity.TopBrandByCity;
import com.adb.adblab.generalservice.entity.TopCityByBrand;
import com.adb.adblab.generalservice.entity.VehicleMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface VehicleMapExtMapper extends Mapper<VehicleMap> {

    @RequestMapping("com.adb.adblab.generalservice.entity.VehicleMap")
    @Select("SELECT * from vehicle_map where cityname like '${cityname}%' and brand_name like '%${brand}%' and address like '%${keyword}%'")
    List<VehicleMap> selectByCityAndBrand(@Param("cityname") String cityname, @Param("brand") String brand, @Param("keyword") String keyword);

    @RequestMapping("com.adb.adblab.generalservice.entity.VehicleMap")
    @Select("SELECT * from vehicle_map where cityname like '${cityname}%' and (address like '%${keyword}%' or brand_name like '%${keyword}%')")
    List<VehicleMap> selectByCity(@Param("cityname") String cityname, @Param("keyword") String keyword);

    @RequestMapping("com.adb.adblab.generalservice.entity.VehicleMap")
    @Select("SELECT * from vehicle_map where brand_name like '%${brandName}%' and address like '%${keyword}%'")
    List<VehicleMap> selectByBrand(@Param("brandName") String brandName, @Param("keyword") String keyword);

//    @RequestMapping("com.adb.adblab.generalservice.entity.CountProvincialNum")
    @Select("SELECT pname,count(*) as number from vehicle_map GROUP BY pname")
    List<Map<String,Object>> selectGroupByPname();

    @Select("SELECT brand_name,count(*) as number from vehicle_map GROUP BY brand_name")
    List<Map<String,Object>> selectGroupByBrandName();

    @Select("SELECT t.cityname from (SELECT cityname,count(*) from vehicle_map GROUP BY cityname) t")
    List<String> getAllCity();

    @Select("select t.cityname as cityname,t.brand_name as brandName,t.num as shopNum from (SELECT cityname,brand_name , count(brand_name) as num from vehicle_map WHERE cityname =#{cityname} GROUP BY brand_name) t ORDER BY t.num desc limit 5")
    List<TopBrandByCity> selectTopBrandByCityname(@Param("cityname") String cityname);

    @Select("select t.brand_name as brandName,t.cityname as cityname,t.num as shopNum from (SELECT brand_name , cityname, count(brand_name) as num from vehicle_map WHERE brand_name = #{brandname} GROUP BY cityname) t ORDER BY t.num desc limit 10")
    List<TopCityByBrand> selectTopCityByBrand(@Param("brandname") String brandname);


}