package com.adb.adblab.generalservice.service;

import com.adb.adblab.generalservice.entity.VehicleMap;
import com.adb.adblab.generalservice.mapper.VehicleMapExtMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopLocationService {

    private static String urlNameString = "https://restapi.amap.com/v3/place/text?";

    @Autowired
    private VehicleMapExtMapper vehicleMapExtMapper;
    @Autowired
    private QueryVehShopService queryVehShopService;

    /**
     * @param keyword
     * @param city
     * @return
     */
    public int getLocationFromAmap(String keyword,String city){

        int count =0;
        int i=1;
        while(true){
            String getUrl = getNewUrl(keyword, city, i);
            try {
                HttpGet request = new HttpGet(getUrl);//这里发送get请求
                HttpClient httpClient = new DefaultHttpClient();
                // 通过请求对象获取响应对象
                HttpResponse response = httpClient.execute(request);

                // 判断网络连接状态码是否正常(0--200都数正常)
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    String result= EntityUtils.toString(response.getEntity(),"utf-8");

                    JSONObject mapJson = JSONObject.parseObject(result);
                    count = Integer.valueOf(mapJson.getString("count"));
                    JSONArray pois = mapJson.getJSONArray("pois");

                    System.out.println(result);
                    insertBatch(pois);

                    if(pois.size() < 25){
                        break;
                    }
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return count;
    }

    private String getNewUrl(String keyword, String city, int page){
        StringBuffer getUrl = new StringBuffer(urlNameString);
        getUrl.append("keywords=").append(keyword).append("&city=").append(city).append("&output=json")
                .append("&offset=25&key=cf2ce6129415e8ff02e6e729eb6339b2&extensions=all").append("&page=").append(page);
        System.out.println("getUrl = "+getUrl);
        return getUrl.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(JSONArray pois){
        for (int i=0 ; i<pois.size() ;i++){
            JSONObject vm = pois.getJSONObject(i);
            VehicleMap vehicleMap = JSONObject.parseObject(vm.toJSONString(), VehicleMap.class);
            VehicleMap svm = vehicleMapExtMapper.selectByPrimaryKey(vehicleMap.getId());

            if(svm == null){
                vehicleMapExtMapper.insertSelective(vehicleMap);
            }else {
               // vehicleMapExtMapper.updateByPrimaryKey(vehicleMap);
                System.out.println(JSON.toJSON(vehicleMap));
            }

        }
    }

    public void insetAllProData(){
        JSONArray pros = queryVehShopService.selectProvincials().getJSONArray("data");
        JSONArray vehNames = queryVehShopService.selectVehNames().getJSONArray("data");


        for(int i=0 ; i<pros.size(); i++){
            for(int j=6 ; j<vehNames.size() ;j++){
                getLocationFromAmap(vehNames.getString(j),pros.getString(i));
            }
        }
    }


}
