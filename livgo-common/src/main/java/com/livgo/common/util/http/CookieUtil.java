package com.livgo.common.util.http;


import com.livgo.common.Const;
import com.livgo.common.util.valid.ValidUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:Cookie工具类
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class CookieUtil {

    public static final int COOKIE_MAXAGE = 30 * 60;

    public static void addCookie(HttpServletResponse response, String name, String value) {

        try {
            // js decodeURIComponent() 解码

            if (!ValidUtil.isEmpty(value)) {
                //cookie中包含有等号、空格、分号等特殊字符时，可能会导致数据丢失、或者不能解析的错误, 需编码
                value = URLEncoder.encode(value, Const.ENCODE);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAXAGE);
        response.addCookie(cookie);

    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        try {
            if (!ValidUtil.isEmpty(value)) {
                value = URLEncoder.encode(value, Const.ENCODE);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static void updCookie(HttpServletRequest request, HttpServletResponse response, String name, String newValue) {
        try {
            if (!ValidUtil.isEmpty(newValue)) {
                newValue = URLEncoder.encode(newValue, Const.ENCODE);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue(newValue);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void updCookie(HttpServletRequest request, HttpServletResponse response, String name, String newValue, int maxAge) {
        try {
            if (!ValidUtil.isEmpty(newValue)) {
                newValue = URLEncoder.encode(newValue, Const.ENCODE);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue(newValue);
                    cookie.setPath("/");
                    if (maxAge > 0) cookie.setMaxAge(maxAge);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static void updCookieMaxAge(HttpServletRequest request, HttpServletResponse response, String name, int maxAge) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setPath("/");
                    if (maxAge > 0) cookie.setMaxAge(maxAge);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        if (name == null) return null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    try {
                        if (!ValidUtil.isEmpty(cookie.getValue())) {
                            return URLDecoder.decode(cookie.getValue(), Const.ENCODE);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

            }
        }
        return null;
    }

    private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }


    public static void refushCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Map<String, Cookie> cookieMap = getCookieMap(request);
        Cookie cookie = cookieMap.get(key);
        cookie.setMaxAge(COOKIE_MAXAGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
