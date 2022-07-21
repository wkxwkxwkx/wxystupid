package src.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkx
 */
public class PublicMessage {
    public static Map<String, String> map = new HashMap<String, String>(10);
    static  {
        map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//       map.put("Accept-Encoding", "gzip, deflate, br");
        map.put("Accept-Language", "zh-CN,zh;q=0.9");
        map.put("Cache-Control", "max-age=0");
        map.put("Connection", "keep-alive");
        map.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
    }
}
