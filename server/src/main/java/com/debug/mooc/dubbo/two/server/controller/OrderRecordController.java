package com.debug.mooc.dubbo.two.server.controller;

import com.debug.mooc.dubbo.one.api.response.BaseResponse;
import com.debug.mooc.dubbo.two.server.request.PushOrderRequest;
import com.debug.mooc.dubbo.two.server.service.OrderRecordService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: {@link OrderRecordController}
 * create by:  xyf
 * description: 订单控制器
 * create time: 2019/12/5 16:43
 */
@RestController
public class OrderRecordController {

    private static final Logger log = LoggerFactory.getLogger(OrderRecordController.class);

    private static final String prefix = "order";

    @Autowired
    private OrderRecordService orderRecordService;


    /**
     * description: 用户下单
     * create time: 2019/12/5 16:45
     * $params []
     * $return java.util.Map<java.lang.String,java.lang.Object>
     *
     * @Author xyf
     */
    @RequestMapping(value = prefix + "/record/push", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Map<String, Object> pushRecord(@RequestBody PushOrderRequest pushOrderRequest) {

        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("code", "0");
        resMap.put("msg", "成功！");
        try {
            log.info("接受到请求数据:{}", pushOrderRequest);
            //todo 处理用户下单数据-发起用户下单接口的调用
        } catch (Exception e) {
            log.error("面向客户:用户下单-发生异常：", e.fillInStackTrace());
            resMap.put("code", "-1");
            resMap.put("msg", "用户下单失败！");
        }
        return resMap;
    }

}
