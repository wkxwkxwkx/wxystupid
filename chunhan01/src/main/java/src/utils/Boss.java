package src.utils;
import java.util.Map;

public class Boss {
    static Map<String, String> map = PublicMessage.map;
    static  {
        map.put("Cookie", "__zp_stoken__=f5a9dYVoYN2Fedm9ZSxZgHhInOXgsdwVvNTpkX3p5JWZwAmdzMDpubSNnc31UEGNtLSYJeFJ6OwdddC0YODRuMFZ");
    }
    static HttpUtils httpUtils = new HttpUtils();
    public static void main(String[] args){
        String html = httpUtils.doGetHtml("https://www.zhipin.com/web/geek/job?query=", map);
//        String clientStr = new String(html.getBytes(), "UTF-8");
//        String clientStr1 = new String(html.getBytes(), "GBK");
        System.out.println(html);
    }
}
