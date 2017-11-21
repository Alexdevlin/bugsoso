package com.livgo.common.util.tip;

import com.livgo.common.Const;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * Description:提示工具类
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class TipUtil {

    private static final ResourceBundle resource = ResourceBundle.getBundle("tip");

    public static String get(String key) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), Const.ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String get(String key, Object... params) {
        String str = "";
        try {
            str = new String(resource.getString(key).getBytes("ISO-8859-1"), Const.ENCODE);
            return String.format(str, params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
