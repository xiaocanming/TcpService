package com.xcm.tcpservice.common.util;

import okhttp3.*;

import java.io.IOException;

/**
 * @描述 http请求客户端
 * @创建人 xcm
 * @创建时间 2021/3/18
 */
public class HttpClient {
    private static MediaType mediaType = MediaType.parse("application/json");

    public static Response call(OkHttpClient okHttpClient, String params, String url) throws IOException {
        RequestBody requestBody = RequestBody.create(mediaType, params);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        return response;
    }
}
