package com.taobao.fanli.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by Tao on 2018/4/19.
 */
public class CookieUtil {

    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain) {
        setCookie(response, cookieName, cookieValue, domain, 7200);
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, int maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    /***
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
        HashMap<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static HashMap<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        HashMap<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
