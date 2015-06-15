package com.example.wangzhaoyu.myguokr.core.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author wangzhaoyu
 */
public class NetUtil {

    /**
     * @param baseUrl
     * @param params
     * @return
     */
    public static String generateFullUrl(String baseUrl, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);

        if (null == params) {
            return sb.toString();
        } else {
            sb.append("?");
            for (String key : params.keySet()) {
                try {
                    sb.append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            return sb.toString().substring(0, sb.length() - 1);
        }
    }
}
