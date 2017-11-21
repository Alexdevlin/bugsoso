package com.livgo.tools.soso.utils.search;

import com.livgo.common.util.valid.ValidUtil;
import com.livgo.tools.soso.model.bean.SearchResultBean;
import com.livgo.common.util.http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Livgo on 2017-10-31.
 */
public class BaiduSearchUtil {

    public static List<SearchResultBean> search(String q, int pnum) {
        List<SearchResultBean> searchResults = new ArrayList<>();
        q = URLEncoder.encode(q);
        String url = "http://www.baidu.com/s?ie=utf-8&&wd=" + q + "&pn=" + (pnum - 1) * 10;
        String result = HttpClient.GETRequest(url);
        Document doc = Jsoup.parse(result, "http://www.baidu.com");
        Elements elements = doc.select("#content_left div.result");
        for (Element el : elements) {
            SearchResultBean sr = new SearchResultBean();
            Element elTitle = el.getElementsByTag("h3").get(0).getElementsByTag("a").get(0);
            sr.setTitle(elTitle.html());
            sr.setLinkUrl(getRedirectUrl(elTitle.attr("href")));
            Elements e3 = el.getElementsByAttributeValueMatching("class", "c-abstract");
            if (ValidUtil.isNotEmpty(e3)) {
                sr.setIntroContent(e3.get(0).html());
            }
            searchResults.add(sr);
        }
        return searchResults;
    }

    public static void main(String[] args) throws Exception {
        String q = "dd ff";
        search(q, 1);
    }

    public static String getRedirectUrl(String path) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path)
                    .openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(3000);
            return conn.getHeaderField("Location");
        } catch (Exception e) {
            return null;
        }
    }

//    public static String get(String url) throws Exception {
//        WebClient wc = new WebClient(BrowserVersion.CHROME);
////        WebClient wc = new WebClient();
//        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
//        wc.getOptions().setCssEnabled(false); //禁用css支持
//        // 设置Ajax异步处理控制器即启用Ajax支持
//        wc.setAjaxController(new NicelyResynchronizingAjaxController());
//        // 当出现Http error时，程序不抛异常继续执行
//        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        // 防止js语法错误抛出异常
//        wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
//        wc.getOptions().setTimeout(30000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
//        wc.waitForBackgroundJavaScript(10000);
//        wc.getOptions().setRedirectEnabled(true);
//
//        HtmlPage page = wc.getPage(url);
//        String pageXml = page.asXml(); //以xml的形式获取响应文本
//        wc.closeAllWindows();
//        return pageXml;
//    }
}
