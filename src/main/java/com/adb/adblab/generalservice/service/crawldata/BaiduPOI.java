package com.adb.adblab.generalservice.service.crawldata;

import com.adb.adblab.generalservice.entity.BaiduMap;
import com.adb.adblab.generalservice.entity.VehicleMap;
import com.adb.adblab.generalservice.mapper.BaiduMapMapper;
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
public class BaiduPOI {
    private static String urlNameString = "http://api.map.baidu.com/place/v2/search?";

    @Autowired
    BaiduMapMapper baiduMapMapper;
    /**
     * @param keyword
     * @param city
     * @return
     */
    public int getLocationFromAmap(String keyword,String city){

        int count =0;
        int i=0;
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
                    count = Integer.valueOf(mapJson.getString("total"));
                    JSONArray pois = mapJson.getJSONArray("results");

                    System.out.println(result);
                    insertBatch(pois);

                    if(pois.size() < 20){
                        break;
                    }
                    i++;
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return count;
    }

    private String getNewUrl(String query, String region, int page){
        StringBuffer getUrl = new StringBuffer(urlNameString);
        getUrl.append("query=").append(query).append("&region=").append(region).append("&output=json")
                .append("&page_size=20&ak=N2vGGNXbvUATh66OcNQo4V78YVXTA8Dk&scope=2").append("&page_num=").append(page);
        System.out.println("getUrl = "+getUrl);
        return getUrl.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(JSONArray pois){
        for (int i=0 ; i<pois.size() ;i++){
            JSONObject vm = pois.getJSONObject(i);
            BaiduMap baiduMap = JSONObject.parseObject(vm.toJSONString(), BaiduMap.class);
//            vehicleMap.setPhotos(null);
            BaiduMap bp = baiduMapMapper.selectByPrimaryKey(baiduMap.getUid());

            if(bp == null){
                baiduMapMapper.insertSelective(baiduMap);
            }else {
                // vehicleMapExtMapper.updateByPrimaryKey(vehicleMap);
                System.out.println(JSON.toJSON(baiduMap));
            }
        }
    }

}
