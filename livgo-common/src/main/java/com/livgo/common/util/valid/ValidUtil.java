package com.livgo.common.util.valid;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 参数验证工具类
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class ValidUtil {


    /**
     * 比较2个字符是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEquals(String str1, String str2) {
        if (isEmpty(str1) || !str1.equals(str2)) {
            return false;
        }
        return true;
    }

    /**
     * 比较2个字符是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isNotEquals(String str1, String str2) {
        return !isEquals(str1, str2);
    }

    /**
     * 是否有特殊字符
     *
     * @param str
     * @return
     */
    public static boolean isIllegalSymbol(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find())
            return true;
        return false;
    }

    /**
     * 是否为指定来源
     *
     * @param referer
     * @param domains
     * @return
     */
    public static boolean isReferer(String referer, String[] domains) {
        if (!StringUtils.isEmpty(referer) && null != domains && domains.length >= 1) {
            for (String domain : domains) {
                if (domain.indexOf(referer) > -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "undefined".equals(str) || "null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.size() <= 0;
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() <= 0;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 验证退款数据是否存在特殊字符
     *
     * @param str
     * @return false校验不通过存在特殊字符
     */
    public static boolean validRefundChar(String str) {
        if (str.indexOf("#") > -1 || str.indexOf("^") > -1 || str.indexOf("$") > -1 || str.indexOf("|") > -1)
            return false;
        return true;
    }

//    /**
//     * 护照校验
//     * @param str
//     * @return
//     */
//    public static boolean isPassport(String str) {
//        // 护照验证不校验
//        String regExp = "^((P|p)\\d{7})|((G|g)\\d{8})$";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(str.trim());
//        return m.matches();
//    }

//    /**
//     * 身份证校验
//     *
//     * @param str
//     * @return
//     */
//    public static boolean isIdCard(String str) {
//        str = str == null ? "" : str.trim();
//        String result = null;
//        try {
//            result = new IDCard().IDCardValidate(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        return "".equals(result) ? true : false;
//    }

    public static boolean isRightUname(String uname) {
        if(isEmpty(uname)){
            return false;
        }
        String regExp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$"; //字母+数字,要求密码长度6-20位
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(uname.trim());
        return m.matches();
    }


    /**
     * 密码验证
     *
     * @param pwd
     * @return
     */
    public static boolean isRightPwd(String pwd) {
        if(isEmpty(pwd)){
            return false;
        }
        // 类似 aB34567!
        //要求密码长度6-18位，1个数字，1个大写字母和一些小写字母, 包含至少1个特殊字符，
//        String regExp ="(?=^.{6,11}$)(?=(?:.*?\\d){1})(?=.*[a-z])(?=(?:.*?[A-Z]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\\s)[0-9a-zA-Z!@#$%*()_+^&]*$"; //字母+数字+特殊字符
//        String regExp ="^(?!(?:[^a-zA-Z]+|\\D|[a-zA-Z0-9])$).{6,11}$"; //字母+数字
        String regExp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$"; //字母+数字,要求密码长度6-20位
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(pwd.trim());
        return m.matches();
    }

    /**
     * 手机号验证
     *
     * @param mobile 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     *               联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
     * @return
     */
    public static boolean isMobile(String mobile) {
        boolean flag = false;
        try {
            // String regExp = "^((177)|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
            // String regExp = "/^1[3|4|5|6|7|8][0-9]\\d{8}$/";//16是声讯号
            String regExp = "^1[3|4|5|7|8][0-9]\\d{8}$";// 147
            Pattern p = Pattern.compile(regExp);
            mobile = mobile.replaceAll(" ", "");
            Matcher m = p.matcher(mobile.trim());
            return m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /*
     * 验证邮箱地址是否正确
     *
     * @param email
     *
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

}
