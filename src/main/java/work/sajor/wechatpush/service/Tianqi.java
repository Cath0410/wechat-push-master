package work.sajor.wechatpush.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import work.sajor.wechatpush.util.HttpUtil;

/**
 * @ClassName Tianqi
 * @Description TODO
 * @Author ydzhao
 * @Date 2022/8/2 16:45
 */
@Service
public class Tianqi {

    @Value("${weather.ak}")
    private String ak;

    public JSONObject getNanjiTianqi() {//北京
        String result = null;
        JSONObject today = new JSONObject();
        try {
            result = HttpUtil.getUrl("http://apis.juhe.cn/simpleWeather/query?city=%E5%8C%97%E4%BA%AC&key=" + ak);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("reason").equals("查询成功!")) {
                JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("future");
                today = arr.getJSONObject(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }

    public JSONObject getNanjiTianqi2() {//深圳
        String result = null;
        JSONObject today = new JSONObject();
        try {
            result = HttpUtil.getUrl("http://apis.juhe.cn/simpleWeather/query?city=%E6%B7%B1%E5%9C%B3&key=" + ak);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("reason").equals("查询成功!")) {
                JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("future");
                today = arr.getJSONObject(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }
}
