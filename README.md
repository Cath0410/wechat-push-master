# 微信每日早安推送 简单部署一键启动

本仓库使用流水线定时帮我们调用测试代码，无需自己搭建服务，使用Gitee免费的流水线来完成。

原博客 [csdn博客](https://blog.csdn.net/jx_ZhangZhaoxuan/article/details/126245804) 使用docker一键部署。


### [Gitee](https://gitee.com/simeitol-sajor/wechat-push) 源码

首先大家需要先注册一个属于自己的 [Gitee](https://gitee.com/signup) 账号。

![](doc/16600600303365.jpg)

登陆之后访问这个 [wechat-push](https://gitee.com/simeitol-sajor/wechat-push) 项目，点击 **star** 这步非常重要！(手动狗头)

![img.png](doc/img.png)

之后点右上角fork到自己的仓库，不需要克隆到本地。

![](doc/16600600806331.jpg)


### API申请

我们需要申请一下开发API所需要的key。

[百度天气API](https://lbsyun.baidu.com/apiconsole/center#/home)
[彩虹屁API](https://www.tianapi.com/apiview/181)

以及最重要的[微信测试账号](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)


### 修改统一配置文件

上面账号申请好之后，到码云访问这个文件路径 `src/main/resources/application.properties`找到配置文件，点击编辑。

![](doc/16600603138459.jpg)

`wechat.appId`, `wechat.secret`是微信测试号的信息
![](doc/16608156645365.jpg)

`target.openId` 填你们对象的微信id，访问`http://localhost:9999/push`即可推送至她的手机。定时推送也用的是这个。

`target.test.openId` 可以填自己的微信id，访问`http://localhost:9999/push/test` 这个地址，会给自己的微信推送，方便我们测试配置文件以及程序的正确性。

![](doc/16607005947205.jpg)

模板id，注意**配置文件里的内容一行都不能删**，有需求的话是改这里的模板内容。

![](doc/16607006249896.jpg)


之后点击下面的提交按钮。提交信息随便填。

![](doc/16600603945433.jpg)


### 构建刘师兄


之后我们就进入构建流水线环节了，我们需要点击码云项目上面的流水线。

![](doc/16600606326903.jpg)


点击开通，无法开通的需要[验证手机号](https://gitee.com/profile/account_information)

![](doc/16600606023300.jpg)

点击不创建。

![](doc/16600606735508.jpg)

之后，我们点击左上角的创建流水线，在代码视图下，替换成下面的代码。最后选择master分支，随便起名保存。

``` yaml
version: '1.0'
name: push-pipeline
displayName: PushPipeline
triggers:
  trigger: auto
  push:
    branches:
      include:
        - .*
      exclude:
        - master
  schedule:
  # 时间 八点 
    - cron: '* 0 8 * * ?'
stages:
  - name: compile
    displayName: 编译
    strategy: naturally
    trigger: auto
    steps:
      - step: ut@maven
        name: unit_test_maven
        displayName: Maven 单元测试
        jdkVersion: '8'
        mavenVersion: 3.3.9
        commands:
          - '# Maven单元测试默认命令'
          - mvn -B test
          - mvn surefire-report:report-only
          - mvn site -DgenerateReports=false
        report:
          path: ./target/site
          index: surefire-report.html
        checkpoints: []
        settings: []
        caches:
          - ~/.m2
        notify: []
        strategy:
          retry: '0'

```

创建好之后，我们选择PushPipeline，执行流水线

![](doc/16600609605502.jpg)

等待流水线执行完毕之后，我们可以看日志。

![WX20221223-181037@2x.png](doc/WX20221223-181037%402x.png)




### 常见错误

没推送出来，首先就要看日志。日志会有错误提示。

- errcode=40037 就是模板`wechat.tamplateId`有问题
- errcode=40003 就是公众号的信息`wechat.appId or wechat.secret or target.openId`有问题
- 天气出不来就是 百度天气api 的`weather.ak`有问题，要选服务端，ip用0.0.0.0/0

### 最后

欢迎大家关注我新注册的微信公众号，关注的同学多了，以后我可能拓展出加更有趣的功能。

大家有什么问题也可以在公众号里私信我，我看到都会回复的。

新增：回复 **早安推送** 可以下载win端可执行文件，双击即可发送消息

![](doc/qrcode_for_gh_4b2bc81b1b42_258.jpg)