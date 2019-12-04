package com.debug.mooc.dubbo.two.server.controller;

import com.debug.mooc.dubbo.one.api.response.BaseResponse;
import com.debug.mooc.dubbo.one.api.service.IDubboItemService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: ItemController
 * create by:  xyf
 * description: 订单控制器
 * create time: 2019/12/4 19:48
 */
@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Autowired
    private IDubboItemService dubboItemService;

    @RequestMapping(value = prefix + "/list", method = RequestMethod.GET)
    private Map<String, Object> list() {
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("code", "0");
        resMap.put("msg", "成功！");
        try {
            BaseResponse baseResponse = dubboItemService.listItems();
            if (baseResponse != null && baseResponse.getCode().equals(0)) {
                resMap.put("data", baseResponse.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("code", "-1");
            resMap.put("msg", "失败！");
        }
        return resMap;
    }


    @RequestMapping(value = prefix + "/list/page", method = RequestMethod.GET)
    private Map<String, Object> listPage(Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0) {
            pageNo = 1;
            pageSize = 2;
        }
        Map<String, Object> resMap = Maps.newHashMap();
        resMap.put("code", "0");
        resMap.put("msg", "成功！");
        try {
            BaseResponse baseResponse = dubboItemService.listPageItems(pageNo, pageSize);
            if (baseResponse != null && baseResponse.getCode().equals(0)) {
                resMap.put("data", baseResponse.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("code", "-1");
            resMap.put("msg", "失败！");
        }
        return resMap;
    }
}
