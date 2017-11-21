package com.livgo.tools.soso.utils.search;

import com.livgo.common.util.valid.ValidUtil;
import com.livgo.tools.soso.model.bean.SearchResultBean;
import com.livgo.common.util.http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Livgo on 2017-10-31.
 */
public class CnblogsSearchUtil {


    public static List<SearchResultBean> search(String q, int pnum) {
        List<SearchResultBean> searchResults = new ArrayList<>();
        q = URLEncoder.encode(q);
        String url = "http://zzk.cnblogs.com/s/blogpost?Keywords=" + q + "&pageindex=" + pnum;
        String result = HttpClient.GETRequest(url);
        Document doc = Jsoup.parse(result, "http://zzk.cnblogs.com");
        Elements elements = doc.select("#searchResult div.searchItem");
        for (Element el : elements) {
            SearchResultBean sr = new SearchResultBean();
            Element elTitle = el.getElementsByTag("h3").get(0).getElementsByTag("a").get(0);
            sr.setTitle(elTitle.html());
            sr.setLinkUrl(elTitle.attr("href"));
            Elements e3 = el.getElementsByAttributeValueMatching("class", "searchCon");
            if (ValidUtil.isNotEmpty(e3)) {
                sr.setIntroContent(e3.get(0).html());
            }
            searchResults.add(sr);

        }
        return searchResults;
    }

    public static void main(String[] args) throws Exception {
        String q = "第三方";
        search(q, 1);
    }
}
