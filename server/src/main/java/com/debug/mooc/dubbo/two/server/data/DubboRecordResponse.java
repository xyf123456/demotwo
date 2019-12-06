package com.debug.mooc.dubbo.two.server.data;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * ClassName: DubboRecordResponse
 * create by:  xyf
 * description: 用户下单响应的数据实体
 * create time: 2019/12/6 23:40
 */
@Data
@ToString
public class DubboRecordResponse implements Serializable {

    private Integer code;

    private String msg;

    private Integer data;
}
