package com.debug.mooc.dubbo.two.server.service;

import com.debug.mooc.dubbo.two.server.controller.OrderRecordController;
import com.debug.mooc.dubbo.two.server.data.DubboRecordResponse;
import com.debug.mooc.dubbo.two.server.request.PushOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName: {@link OrderRecordService}
 * create by:  xyf
 * description: 订单接口
 * create time: 2019/12/5 17:09
 */
@Service
public class OrderRecordService {

    private static final Logger log = LoggerFactory.getLogger(OrderRecordService.class);
    //单例模式创建OkHttpClient对象
    private OkHttpClient httpClient = new OkHttpClient();

    private static final String URL = "http://localhost:9013/v1/record/push";

    // // 转换为格式化的json的组件
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpService httpService;

    /**
     * description: 手动实现调用下单服务
     * create time: 2019/12/6 23:06
     * [pushOrderRequest]
     *
     * @return void
     */
    public void pushOrder(PushOrderRequest pushOrderRequest) throws Exception {
        try {
            //todo 构造builder
            Request.Builder builder = new Request.Builder()
                    .url(URL)
                    .header("Content-Type", "application/json");
            //todo 构造请求体
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
                    objectMapper.writeValueAsString(pushOrderRequest));
            //todo 构造请求
            Request request = builder.post(requestBody).build();
            //todo 发起请求
            Response response = httpClient.newCall(request).execute();
            //将请求体打印出来
            log.info(request.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * description: 使用封装好的请求完成用户下单服务的调用
     * create time: 2019/12/6 23:11
     * [pushOrderRequest]
     *
     * @return void
     */
    public void pushOrder1(PushOrderRequest pushOrderRequest) throws Exception {
        try {
            Map<String, String> headerMap = Maps.newHashMap();
            headerMap.put("Content-Type", "application/json");
            String res = httpService.post(URL, headerMap, "application/json", objectMapper.writeValueAsString(pushOrderRequest));
            log.info("响应结果:{}", res);
            //todo 得到响应数据需要解析后进行保存或者其他操作
//         todo   map解析 解析针对响应数据比较少的场景
            Map<String, Object> resMap = objectMapper.readValue(res, Map.class);
            log.info("得到响应解析结果:{}", resMap);
            Integer code = (Integer) resMap.get("code");
            String msg = (String) resMap.get("msg");
            Integer data = (Integer) resMap.get("data");
            log.info("code={} msg={} data={}", code, msg, data);

            //todo 对象解析，更加通用,数据量比较复杂的解析
            DubboRecordResponse dubboRecordResponse = objectMapper.readValue(res, DubboRecordResponse.class);
            log.info("得到的响应数据：{}", dubboRecordResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
