package com.debug.mooc.dubbo.two.server.service;

import com.debug.mooc.dubbo.two.server.controller.OrderRecordController;
import com.debug.mooc.dubbo.two.server.request.PushOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ClassName: {@link OrderRecordService}
 * create by:  xyf
 * description: 订单接口
 * create time: 2019/12/5 17:09
 */
@Service
public class OrderRecordService {

    private static final Logger log = LoggerFactory.getLogger(OrderRecordService.class);

    public void pushOrder(PushOrderRequest pushOrderRequest) throws Exception {
        //todo 构造builder
        //todo 构造请求体
        //todo 构造请求
        //todo 发起请求
    }
}
