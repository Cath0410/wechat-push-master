package work.sajor.wechatpush.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import work.sajor.wechatpush.service.Pusher;

@Component
public class JobWorker {
    /**
     * 要推送的用户openid
     */
    @Value("${target.openId}")
    private String openId;
    @Value("${target.openId2}")
    private String openId2;
    @Value("${target.openId3}")
    private String openId3;

    @Autowired
    Pusher pusherService;

    @Scheduled(cron = "0 30 6 ? * *")
    public void sendMorning() {
        pusherService.push(openId);
        pusherService.push2(openId2);
        pusherService.push3(openId3);
    }

    @Scheduled(cron = "0 0 12 ? * *")
    public void sendNoon() {
        pusherService.push(openId);
        pusherService.push2(openId2);
        pusherService.push3(openId3);
    }

    @Scheduled(cron = "0 0 21 ? * *")
    public void sendAfterNoon() {
        pusherService.push(openId);
        pusherService.push2(openId2);
        pusherService.push3(openId3);
    }
}
