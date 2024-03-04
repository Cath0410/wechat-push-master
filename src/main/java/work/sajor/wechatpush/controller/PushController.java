package work.sajor.wechatpush.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.sajor.wechatpush.job.JobWorker;
import work.sajor.wechatpush.service.Pusher;

@RestController
public class PushController {
    /**
     * 要推送的用户openid-lly
     */
    @Value("${target.openId}")
    private String target;

    /**
     * 要推送的用户openid-zwq
     */
    @Value("${target.openId2}")
    private String target2;
    /**
     * 要推送的用户openid-zwq
     */
    @Value("${target.openId3}")
    private String target3;
    @Autowired
    Pusher pusherService;

    /**
     * 微信测试账号推送
     */
    @GetMapping("/push")
    public void push() {
        JobWorker worker = new JobWorker();
        worker.sendMorning();
        worker.sendAfterNoon();
        worker.sendNoon();
//        pusherService.push2(target2);
    }

}
