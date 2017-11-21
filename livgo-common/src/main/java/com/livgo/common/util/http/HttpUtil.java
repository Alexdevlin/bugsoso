package com.livgo.common.util.http;

import com.livgo.common.util.valid.ValidUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:HTTP工具类
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class HttpUtil {


    /**
     * 获得IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ValidUtil.isEmpty(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }

        if (!ValidUtil.isEmpty(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 获取useragent
     *
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        if (request == null)
            return null;
        return request.getHeader("User-Agent");
    }

    /**
     * URL编码
     *
     * @param str
     * @return
     */
    public static String escape(String str) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(str.length() * 6);
        for (i = 0; i < str.length(); i++) {
            j = str.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * URL解码
     *
     * @param str
     * @return
     */
    public static String unescape(String str) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(str.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < str.length()) {
            pos = str.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (str.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(str
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(str
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(str.substring(lastPos));
                    lastPos = str.length();
                } else {
                    tmp.append(str.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }


}
