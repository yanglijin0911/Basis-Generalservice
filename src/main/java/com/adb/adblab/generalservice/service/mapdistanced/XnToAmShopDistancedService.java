package com.adb.adblab.generalservice.service.mapdistanced;

import com.adb.adblab.generalservice.entity.StreetShop;
import com.adb.adblab.generalservice.entity.XnAmShopMinDistance;
import com.adb.adblab.generalservice.mapper.StreetShopExtMapper;
import com.adb.adblab.generalservice.mapper.VehicleMapExtMapper;
import com.adb.adblab.generalservice.mapper.XnAmShopMinDistanceMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class XnToAmShopDistancedService {
    private static double EARTH_RADIUS = 6378.137;
    private static String brandName1 = "小牛电动车";
    private static String brandName2 = "爱玛电动车";

    @Autowired
    VehicleMapExtMapper vehicleMapExtMapper;
    @Autowired
    StreetShopExtMapper streetShopExtMapper;
    @Autowired
    XnAmShopMinDistanceMapper xnAmShopMinDistanceMapper;

    @Transactional(rollbackFor = Exception.class)
    public void getAmaShopOfMinDistanceFromXiaoniuShop(){
        List<XnAmShopMinDistance> output = new ArrayList<>();

        List<String> allCity = vehicleMapExtMapper.getAllCity();
        for (String cityname: allCity) {
            List<StreetShop> xns = streetShopExtMapper.selectByCityNameAndBrandName(cityname, brandName1);
            List<StreetShop> ams = streetShopExtMapper.selectByCityNameAndBrandName(cityname, brandName2);

            if(CollectionUtils.isEmpty(xns)){
                continue;
            }
            if(CollectionUtils.isEmpty(ams)){
                System.out.println(JSON.toJSONString(xns));
                continue;
            }

            for (StreetShop xn : xns) {
                String xnLocation = xn.getLocation();
                List<XnAmShopMinDistance> list = new ArrayList<>();
                for (StreetShop am: ams) {
                    String amLocation = am.getLocation();
                    Double distance = getDistance(xnLocation.split(",")[0], xnLocation.split(",")[1],
                            amLocation.split(",")[0], amLocation.split(",")[1]);
                    XnAmShopMinDistance d = new XnAmShopMinDistance();
                    d.setPname(xn.getPname());
                    d.setCityname(cityname);
                    d.setXnAddress(xn.getAddress());
                    d.setAmAddress(am.getAddress());
                    d.setXnLocation(xn.getLocation());
                    d.setAmLocation(am.getLocation());
                    d.setMinDistance(distance);
                    list.add(d);
                }
                if(list.size() == 1){
                    System.out.println(JSON.toJSONString(xns));
                }
                Collections.sort(list, new Comparator<XnAmShopMinDistance>() {

                    public int compare(XnAmShopMinDistance o1, XnAmShopMinDistance o2) {
                        if (o1.getMinDistance() < o2.getMinDistance()) {
                            return -1;
                        }
                        if (o1.getMinDistance() == o2.getMinDistance()) {
                            return 0;
                        }
                        return 1;
                    }
                });
//                System.out.println(JSON.toJSON(list));
                output.add(list.get(0));
            }
        }
//        for (XnAmShopMinDistance xm:output) {
//            xnAmShopMinDistanceMapper.insertSelective(xm);
//        }
    }


    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param lat1Str 用户经度
     * @param lng1Str 用户纬度
     * @param lat2Str 商家经度
     * @param lng2Str 商家纬度
     * @return
     */
    public Double getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
        Double lat1 = Double.parseDouble(lat1Str);
        Double lng1 = Double.parseDouble(lng1Str);
        Double lat2 = Double.parseDouble(lat2Str);
        Double lng2 = Double.parseDouble(lng2Str);

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = (double) Math.round(distance * 1000) / 1000;
//        String distanceStr = distance+"";
//        distanceStr = distanceStr.
//                substring(0, distanceStr.indexOf("."));

        return distance;
    }

    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();

        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度

        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);

        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;

        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");

        return map;
    }

//    public static void main(String[] args) {
//        //测试经纬度：117.11811  36.68484
//        //121.466190,30.912716
//        //121.222115,31.010269
//        //121.823572,30.905223
//        LocationDistanced locationDistanced = new LocationDistanced();
//        System.out.println(locationDistanced.getDistance("121.466190","30.912716","121.823572","30.905223"));
//
////        System.out.println(getAround("117.11811", "36.68484", "13000"));
//        //117.01028712333508(Double), 117.22593287666493(Double),
//        //36.44829619896034(Double), 36.92138380103966(Double)
//
//    }
}
