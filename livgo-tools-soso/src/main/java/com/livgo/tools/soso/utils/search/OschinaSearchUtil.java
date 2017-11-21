package com.livgo.tools.soso.utils.search;

import com.livgo.common.util.valid.ValidUtil;
import com.livgo.tools.soso.model.bean.SearchResultBean;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Livgo on 2017-10-31.
 */
public class OschinaSearchUtil {

    public static List<SearchResultBean> search(String q, int pnum) {
        List<SearchResultBean> searchResults = new ArrayList<>();
        q = URLEncoder.encode(q);
        String url = "http://www.oschina.net/search?scope=all&q=" + q + "&p=" + pnum + "&fromerr=" + UUID.randomUUID().toString().substring(0, 8);
        String result = crawOschina(url);
//        String result = HttpClient.GETRequest(url);
        Document doc = Jsoup.parse(result, "http://www.oschina.net");
        Elements elements = doc.select("#results li");
        for (Element el : elements) {
            SearchResultBean sr = new SearchResultBean();
            Elements e1 = el.getElementsByTag("h3").get(0).getElementsByTag("em");
            Element elTitle = el.getElementsByTag("h3").get(0).getElementsByTag("a").get(0);
            if (ValidUtil.isNotEmpty(e1)) {
                sr.setTitle(e1.get(0).text() + elTitle.html());
            } else {
                sr.setTitle(elTitle.html());
            }
            sr.setLinkUrl(elTitle.attr("href"));
//            sr.setIntroContent(el.getElementsByAttributeValueMatching("class", "outline").get(0).html());
            Elements e3 = el.getElementsByAttributeValueMatching("class", "outline");
            if (ValidUtil.isNotEmpty(e3)) {
                sr.setIntroContent(e3.get(0).html());
            }
            searchResults.add(sr);
        }
        return searchResults;
    }

    public static void main(String[] args) throws Exception {
        String q = "空指针";
        search(q, 1);
    }

    public static String crawOschina(String url) {
        try {
//                    WebClient wc = new WebClient(BrowserVersion.CHROME);
            WebClient wc = new WebClient();
            wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器
            wc.getOptions().setCssEnabled(false);
            wc.setAjaxController(new NicelyResynchronizingAjaxController());
            wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
            wc.getOptions().setThrowExceptionOnScriptError(false);
            wc.getOptions().setTimeout(30000);
            wc.waitForBackgroundJavaScript(10000);
            wc.getOptions().setRedirectEnabled(true);

            HtmlPage page = wc.getPage(url);
            String pageXml = page.asXml();
            wc.closeAllWindows();

            return pageXml;
        } catch (Exception e) {
            return null;
        }
    }
}
