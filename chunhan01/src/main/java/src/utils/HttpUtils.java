package src.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

/**
 * @author wangkx
 */
public class HttpUtils {
    /**
     *
     * 声明连接池管理器变量cm
     */
    private PoolingHttpClientConnectionManager cm;

    /**
     * 当我们new HttpUtils类时 , 连接池管理器就会被创建
     */
    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        this.cm.setMaxTotal(100);
        // 设计每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     *  根据请求地址下载页面数据
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url, Map<String,String> map){
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        // 创建HttpGet请求 ,设置url地址
        HttpGet httpGet = new HttpGet(url);
        for (String s : map.keySet()) {
            httpGet.setHeader(s,map.get(s));
        }
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response =null;
        try {
            // 使用HttpClient发起请求,获取响应
            response = httpClient.execute(httpGet);
            //解析响应,返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空,如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭response
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
        // 只要有问题就返回空串
        return "";
    }




    /**
     * 下载图片
     * @param url
     * @return 图片名称
     */
    public String doGetImage(String url){
        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        // 创建HttpGet请求 ,设置url地址
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response =null;
        try {
            // 使用HttpClient发起请求,获取响应
            response = httpClient.execute(httpGet);
            //解析响应,返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空
                if (response.getEntity() != null) {
                    // 下载图片
                    // 获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    // 重命名图片
                    String picName = UUID.randomUUID().toString() + extName;
                    // 下载图片
                    // 声明OutPutStream
                    OutputStream outputStream = new FileOutputStream(new File("/Users/wangkx/Desktop/chunhan01/web/resourse/img/" + picName));
                    response.getEntity().writeTo(outputStream);
                    // 返回图片名称
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭response
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;
            }
        }
        // 下载失败就返回空串
        return "";
    }

    /**
     * 设置请求信息
     * @return
     */
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                //创建连接的最长时间
                .setConnectTimeout(1000)
                // 获取连接的最长时间
                .setConnectionRequestTimeout(500)
                // 数据传输的最长时间
                .setSocketTimeout(10000)
                .build();
        return config;
    }

}
