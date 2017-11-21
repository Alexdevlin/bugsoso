//package com.livgo.tools.soso.web;
//
//import com.livgo.common.util.valid.ValidUtil;
//import com.livgo.core.exception.BasicException;
//import com.livgo.model.http.ResponseBean;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.regex.PatternSyntaxException;
//
///**
// * Description:
// * Author:     Livgo
// * Date:       2017/11/20
// * Verion:     V1.0.0
// * Update:     更新说明
// */
//@Controller
//@RequestMapping("soso")
//public class SosoController extends BaseController {
//
//    @RequestMapping(value = "share", method = RequestMethod.POST, produces = "application/json")
//    @ResponseBody
//    public ResponseBean share(String shareLinkName, String shareLinkUrl) {
//        try {
//            if (ValidUtil.isEmpty(shareLinkName) || ValidUtil.isEmpty(shareLinkUrl)) {
//                return ResponseBean.FAIL_ARG();
//            }
//            if (filterStr(shareLinkName) || filterStr(shareLinkUrl)) {
//                return ResponseBean.FAIL_ARG();
//            }
//            String htmlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "static/soso/index.html";
//            htmlPath = htmlPath.replaceAll("%20", " ");
//            String htmlStr = readFile(htmlPath);
//            String replaceStr = "<button class=\"btn btn-default\" onclick=\"shareLinkUrl()\">贡献外链</button>";
//            String shareStr = "</li><li><a href=\"" + shareLinkUrl + "\" target=\"_blank\">" + shareLinkName + "</a>";
//            htmlStr = htmlStr.replace(replaceStr, replaceStr + "\n" + shareStr);
//            writeFile(htmlPath, htmlStr);
//            return ResponseBean.SUCCESS();
//        } catch (Exception e) {
//            throw new BasicException(getChildrenMethodName(), e);
//        }
//    }
//
//    public static void main(String[] args) {
//        String htmlPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "static/soso/index.html";
//        String htmlStr = readFile(htmlPath);
//        System.out.println(htmlStr);
//        String newStr = "</li><li><a href=\"http://www.iovweek.com/\" target=\"_blank\">车联网周刊</a>";
//        String str = "<button class=\"btn btn-default\" onclick=\"shareLinkUrl()\">贡献外链</button>";
//        htmlStr = htmlStr.replace(str, newStr + "\n" + str);
//        writeFile(htmlPath, htmlStr);
//    }
//
//    public static void writeFile(String fileName, String content) {
//        try {
//            FileOutputStream fos = new FileOutputStream(fileName);
//            fos.write(content.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String readFile(String fileName) {
//        try {
//            FileInputStream fin = new FileInputStream(fileName);
//            int length = fin.available();
//            byte[] buffer = new byte[length];
//            fin.read(buffer);
//            fin.close();
//            return new String(buffer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static boolean filterStr(String str) throws PatternSyntaxException {
//        String regEx = "[`~!@#$%^*()+|{}';',\\[\\]<>~！@#￥……*（）——+|{}【】‘；：”“’。，、？]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(str);
//        return m.find();
//    }
//
//}
