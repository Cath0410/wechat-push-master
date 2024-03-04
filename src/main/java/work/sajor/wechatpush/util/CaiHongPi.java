package work.sajor.wechatpush.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaiHongPi {
    private static String url = "https://apis.tianapi.com/caihongpi/index?key=";
    private static String url2 = "http://apis.juhe.cn/fapigx/mingyan/query?num=5&typeid=%E5%8A%B1%E5%BF%97&key=a4f757ac6a7188aaa1beead2ed9edc3c";
    private static List<String> jinJuList = new ArrayList<>();

    public static String getCaiHongPi(String key, String name) {
        //默认彩虹屁
        String str = "阳光落在屋里，爱你藏在心里";
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(url + key).replace("XXX", name));
            if (jsonObject.getIntValue("code") == 200) {
                JSONObject a = jsonObject.getJSONObject("result");
                System.out.println(a.get("content"));
                str = a.get("content").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank((CharSequence) str)) {
            return "希望你每天都开心，或者至少不会太难过 --by 01";
        }
        return str;
    }


    public static String getJinJu() {
        String jin = "";
        try {
            JSONObject jsonObject2 = JSONObject.parseObject(HttpUtil.getUrl(url2));
            if (jsonObject2.get("reason").equals("success")) {
                JSONObject a = jsonObject2.getJSONObject("result");
                List<JSONObject> x = (List<JSONObject>) a.get("list");
                for (int i = 0; i < x.size(); i++) {
                    jin += x.get(i).getString("content") + ";";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank((CharSequence) jin)) {
            return "你眼中的别人才是你  世界是一面巨大的镜子 宝宝 --by 01";
        }

        return jin;
    }

    public static void main(String[] args) {
        System.out.println(getJinJu());
//        System.out.println(getCaiHongPi());
    }
}
