package com.debug.mooc.dubbo.two.server.service;

import com.google.common.base.Strings;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static okhttp3.Request.*;

/**
 * ClassName: HttpService
 * create by:  xyf
 * description: http通信服务类
 * create time: 2019/12/6 22:28
 */
@Service
public class HttpService {

    private static final Logger log = LoggerFactory.getLogger(HttpService.class);

    private OkHttpClient client = new OkHttpClient();

    /**
     * description: 获取Request对象
     * create time: 2019/12/6 22:36
     * [url, headerMap]
     *
     * @return okhttp3.Request
     */
    private Request commonGetRequest(String url, Map<String, String> headerMap) throws Exception {
        Request request;
        Builder builder = new Builder();
        if (headerMap != null && headerMap.keySet() != null && headerMap.keySet().size() > 0) {
            Headers headers = Headers.of(headerMap);
            request = builder.get()
                    .url(url)
                    .headers(headers)
                    .build();
        } else {
            request = builder.get().url(url).build();
        }

        return request;
    }


    /**
     * description: 获取Request.Builder对象
     * create time: 2019/12/6 22:39
     * [url, headerMap]
     *
     * @return okhttp3.Request.Builder
     */
    private Builder commonPostBuilder(String url, Map<String, String> headerMap) throws Exception {
        Builder builder;

        if (headerMap != null && headerMap.keySet() != null && headerMap.keySet().size() > 0) {
            Headers headers = Headers.of(headerMap);
            builder = new Builder()
                    .url(url)
                    .headers(headers);
        } else {
            builder = new Builder().url(url);
        }
        return builder;
    }


    /**
     * description: get请求
     * create time: 2019/12/6 22:42
     * [url, headerMap] 请求url  请求头map
     *
     * @return java.lang.String
     */
    public String get(String url, Map<String, String> headerMap) throws Exception {
        Request request = commonGetRequest(url, headerMap);

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error("发送同步-get请求发生异常：url={} ", e.fillInStackTrace());
        }
        return null;
    }

    /**
     * description: post请求
     * create time: 2019/12/6 22:41
     * [url, headerMap, contentType, data] 请求Url 请求头map 请求内容类型 请求体数据-对象序列化后的字符串格式数据
     *
     * @return java.lang.String
     */
    public String post(String url, Map<String, String> headerMap, String contentType, String data) throws Exception {
        Builder builder = commonPostBuilder(url, headerMap);

        Request request;
        RequestBody requestBody;
        if (!Strings.isNullOrEmpty(data) && !Strings.isNullOrEmpty(contentType)) {
            requestBody = RequestBody.create(MediaType.parse(contentType), data);
            request = builder.post(requestBody).build();
        } else {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            request = builder.post(bodyBuilder.build()).build();
        }

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * description: post请求 无请求体
     * create time: 2019/12/6 22:43
     * [url]
     *
     * @return java.lang.String
     */
    public String post(String url) throws Exception {
        Request.Builder builder = new Request.Builder().url(url);
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        Request request = builder.post(bodyBuilder.build()).build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * description: post请求
     * create time: 2019/12/6 22:44
     * [url, headerMap, bodyParams]       请求体数据-map格式
     *
     * @return java.lang.String
     */
    public String post(String url, Map<String, String> headerMap, Map<String, String> bodyParams) throws Exception {
        Request.Builder builder = commonPostBuilder(url, headerMap);

        RequestBody body = setRequestBody(bodyParams);
        Request request = builder
                .post(body)
                .url(url)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * description: post请求（orm表单提交）
     * create time: 2019/12/6 22:44
     * [params]
     *
     * @return okhttp3.RequestBody
     */
    private RequestBody setRequestBody(Map<String, String> params) {
        RequestBody body = null;
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.keySet() != null && params.keySet().size() > 0) {
            String key;
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formBuilder.add(key, params.get(key));
            }
        }
        body = formBuilder.build();

        return body;
    }

}
