package com.livgo.common.util.http;

import com.livgo.common.util.valid.ValidUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Description:HTTP客户端
 * Author:     Livgo
 * Date:       2017/11/15
 * Verion:     V1.0.0
 * Update:     更新说明
 */
public class HttpClient {

    public static String get(String url) throws IOException {

        String responseBody = "";
        CloseableHttpClient httpclient;
        httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {

                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpclient.execute(httpget, responseHandler);
        } finally {
            httpclient.close();
        }

        return responseBody;
    }

    public static String post(String url, String returnUrl, List<NameValuePair> params)
            throws IOException {

        String responseBody = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();

                        return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            responseBody = httpclient.execute(httpPost, responseHandler);
        } finally {
            httpclient.close();
        }

        return responseBody;
    }

    public static String post(String url, String data) throws IOException {

        String responseBody = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new StringEntity(data));

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();

                        return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            responseBody = httpclient.execute(httpPost, responseHandler);
        } finally {
            httpclient.close();
        }

        return responseBody;
    }

    public static String POSTRequest(String path, String param) {
        try {
            URL url = new URL(path.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(60000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            if (param != null && !"".equals(param)) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(param.getBytes("UTF-8"));
                out.flush();
                out.close();
            }
            byte[] data = readStream(conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream());
            String json = new String(data, "UTF-8");
            return json;
        } catch (Exception e) {
            return "";
        }
    }


    public static String GETRequest(String path) {
        try {
            URL url = new URL(path.toString());
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String l = "";
            StringBuilder sb = new StringBuilder();
            while ((l = br.readLine()) != null) {
                sb.append(l);
            }
            br.close();

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inputStream.close();
        return outStream.toByteArray();
    }

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        byte bytes[] = new byte[request.getContentLength()];
        is.read(bytes);
        String body = new String(bytes, request.getCharacterEncoding());
        return body;
    }

    /**
     * 格式化POST参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String formatParams(String url, Map<String, ?> params) {
        boolean start = url.indexOf("?") != -1;
        String getUrl = url;
        if (ValidUtil.isNotEmpty(params)) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                if (!start) {
                    getUrl += "?";
                    start = true;
                } else {
                    getUrl += "&";
                }
                getUrl += entry.getKey().concat("=").concat(entry.getValue().toString());
            }
        }
        return getUrl;
    }


}
