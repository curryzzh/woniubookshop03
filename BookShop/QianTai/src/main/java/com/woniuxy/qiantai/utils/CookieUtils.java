package com.woniuxy.qiantai.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    //把Cookie写到 客户端
    public static void addCookie(HttpServletResponse response, String cookieName,String cookieValue,Boolean delete){
        Cookie c = new Cookie(cookieName,cookieValue);
        //c.setDomain(""); //域名
        c.setPath("/"); //
        if (delete){
            c.setMaxAge(0);
        }
        response.addCookie(c);
    }

    //把token从Cookie获取到
    public static String getCookieValueByName(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();//获取所有的cookie
        if (cookies != null && cookies.length > 0){
            for(Cookie c :cookies){
                if (c.getName().equals(cookieName)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }


    /**
     * 修改request中cookieName的value 为 newCookieValue
     *
     * @param request
     * @param cookieName
     * @param newCookieValue
     * @return
     */
    public static Boolean refreshRequestCookie(HttpServletRequest request,String cookieName,String newCookieValue){

        Boolean result = false;

        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){
            for(Cookie cookie : cookies){
                if (cookie.getName().equalsIgnoreCase(cookieName)){
                    cookie.setValue(newCookieValue);
                    result = true;
                    break;
                }
            }
        }

        return result;
    }


    public static void setUserToken2Cookie(HttpServletResponse response,String userToken){
        addCookie(response,"user_token",userToken,false);
    }

    public static String getUserTokenFromCookie(HttpServletRequest request){
        return getCookieValueByName(request,"user_token");
    }

    public static void deleteUserTokenFromCookie(HttpServletResponse response){
        addCookie(response,"user_token",null,true);
    }

    public static Boolean refreshRequestUserToken(HttpServletRequest request,String userToken){
        return refreshRequestCookie(request,"user_token",userToken);
    }


}

