package com.adb.adblab.generalservice.mapper;

import com.adb.adblab.generalservice.entity.CountStreetShop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CountStreetShopExtMapper extends Mapper<CountStreetShop> {

    @RequestMapping("com.adb.adblab.generalservice.entity.CountStreetShop")
    @Select("select pname,cityname,adname,street_address as streetAddress,number from count_street_shop where number > #{number}")
    List<CountStreetShop> selectByNumber(@Param("number") Integer number);

}