package com.debug.mooc.dubbo.two.server.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName: {@link PushOrderRequest}
 * create by:  xyf
 * description: 下单请求数据实体
 * create time: 2019/12/5 16:52
 */
@Data
@ToString
public class PushOrderRequest implements Serializable {

    private static final long serialVersionUID = -210226428824728739L;
    //商品id
    private Integer itemId;

    //下单数量
    private Integer total;

    //客户姓名
    private String customerName;
}
