package src.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ZhiLian {
    static Map<String, String> map = PublicMessage.map;
    static  {
        map.put("Cookie", "x-zp-client-id=dd5706c4-15b6-4666-b3a5-e7809c12d288; 1420ba6bb40c9512e9642a1f8c243891=48f753b1-1e83-4ab9-9935-3323226126e2");
    }

    static HttpUtils httpUtils = new HttpUtils();
    public static void main(String[] args) throws IOException {
        String before = "__INITIAL_STATE__=";
        String after = "</script>";
//        String html = httpUtils.doGetHtml("https://www.lagou.com/gongsi/0-0--0", map);
        Document html = Jsoup.parse(new File("/Users/wangkx/Desktop/智联.html"),"utf-8");


    }
}
