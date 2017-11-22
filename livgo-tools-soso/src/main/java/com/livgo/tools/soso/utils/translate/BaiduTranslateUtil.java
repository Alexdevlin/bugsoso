package com.livgo.tools.soso.utils.translate;

import com.livgo.common.util.valid.ValidUtil;

/**
 * Created by Livgo on 2017-10-31.
 */
public class BaiduTranslateUtil {

    private static final String APP_ID = "";//自己的APPID
    private static final String SECURITY_KEY = "";//自己的KEY

    public static String fanyi(String query){
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            String result = api.getTransResult(query, "auto", "zh");
            int j = result.indexOf("\"dst\":\"") + 7;
            int k = result.length() - 4;
            return unicode2String(result.substring(j,k));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public static String unicode2String(String unicode){
        if(ValidUtil.isEmpty(unicode)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(fanyi("yes"));
    }
}
