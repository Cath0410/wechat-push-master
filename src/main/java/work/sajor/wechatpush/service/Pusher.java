package work.sajor.wechatpush.service;


import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import work.sajor.wechatpush.util.CaiHongPi;
import work.sajor.wechatpush.util.JiNianRi;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class Pusher {
    /**
     * 测试号的appId和secret
     */
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    //模版id
    @Value("${wechat.templateId}")
    private String templateId;
    //模版id
    @Value("${wechat.templateId2}")
    private String templateId2;
    @Value("${wechat.templateId3}")
    private String templateId3;

    @Value("${tian.caihongpi.key}")
    private String key;
    /**
     * 生日
     */
    @Value("${target.shengRi}")
    private String shengRi;
    @Value("${target.shengRi2}")
    private String shengRi2;
    @Value("${target.shengRi3}")
    private String shengRi3;


    @Autowired
    Tianqi tianqiService;

    //lly
    public void push(String openId) {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
//        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
//        templateMessage.addData(new WxMpTemplateData("lly001", "111", "#FF00FF"));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = tianqiService.getNanjiTianqi();
        String weather = todayWeather.getString("weather");
        if (StringUtils.isBlank((CharSequence) weather)) {
            weather = "永远明亮";
        }
        String direct = todayWeather.getString("direct");
        if (StringUtils.isBlank((CharSequence) direct)) {
            direct = "积极向上";
        }
        String date = todayWeather.getString("date");
        if (StringUtils.isBlank((CharSequence) date)) {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.format(d);
        }
        String temperature = todayWeather.getString("temperature");
        if (StringUtils.isBlank((CharSequence) temperature)) {
            temperature = "不到100";
        }
        templateMessage.addData(new WxMpTemplateData("riqi", date, "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather + " " + direct, "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("temperature", temperature, "#173177"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(key, "李01"), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRi.getShengRi(shengRi) + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju", CaiHongPi.getJinJu(), "red"));
        templateMessage.addData(new WxMpTemplateData("hope", "身体健康 發財發財", "#FF0000"));
        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //zwq
    public void push2(String openId2) {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId2)
                .templateId(templateId2)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = tianqiService.getNanjiTianqi2();
        String weather = todayWeather.getString("weather");
        if (StringUtils.isBlank((CharSequence) weather)) {
            weather = "永远明亮";
        }
        String direct = todayWeather.getString("direct");
        if (StringUtils.isBlank((CharSequence) direct)) {
            direct = "积极向上";
        }
        String date = todayWeather.getString("date");
        if (StringUtils.isBlank((CharSequence) date)) {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.format(d);
        }
        String temperature = todayWeather.getString("temperature");
        if (StringUtils.isBlank((CharSequence) temperature)) {
            temperature = "不到100";
        }
        templateMessage.addData(new WxMpTemplateData("riqi", date, "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather + " " + direct, "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("temperature", temperature + "", "#173177"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(key, "尾鳍"), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRi.getShengRi(shengRi2) + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju", CaiHongPi.getJinJu() + "", "#C71585"));
        String beizhu = "发大财";
        templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));
        templateMessage.addData(new WxMpTemplateData("hope", "身体健康 發財發財", "#FF0000"));
        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    //mm
    public void push3(String openId3) {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId3)
                .templateId(templateId)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
//        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
//        templateMessage.addData(new WxMpTemplateData("lly001", "111", "#FF00FF"));
        //填写变量信息，比如天气之类的
        JSONObject todayWeather = tianqiService.getNanjiTianqi();
        String weather = todayWeather.getString("weather");
        if (StringUtils.isBlank((CharSequence) weather)) {
            weather = "永远明亮";
        }
        String direct = todayWeather.getString("direct");
        if (StringUtils.isBlank((CharSequence) direct)) {
            direct = "积极向上";
        }
        String date = todayWeather.getString("date");
        if (StringUtils.isBlank((CharSequence) date)) {
            Date d = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.format(d);
        }
        String temperature = todayWeather.getString("temperature");
        if (StringUtils.isBlank((CharSequence) temperature)) {
            temperature = "不到100";
        }
        templateMessage.addData(new WxMpTemplateData("riqi", date, "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather + " " + direct, "#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("temperature", temperature, "#173177"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPi.getCaiHongPi(key, "亦木"), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRi.getShengRi(shengRi3) + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju", CaiHongPi.getJinJu(), "red"));
        templateMessage.addData(new WxMpTemplateData("hope", "身体健康 發財發財", "#FF0000"));
        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
