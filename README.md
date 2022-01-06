## Hi there 👋

抢夕夕后端，正在完成中...

完成度20 ~ 30%，在做了，在做了。

[![Anurag's GitHub stats](https://github-readme-stats.vercel.app/api?username=zhx47&bg_color=30,e96443,904e95&title_color=fff&text_color=fff)](https://github.com/anuraghazra/github-readme-stats)

## 基础工具

目前本项目还未完成，所以没有配置 docker，想要体验的小伙伴可能需要一些基本的开发工具自行构建。

1. 基本的 Java 开发环境：JDK, Maven, IDEA
2. 数据库链接工具： IDEA / SQLyog/ Navicat 等等，任意挑选一个即可
3. MySQL, Redis
4. 一台用来部署的服务器

## 如何使用
### 数据库基本配置

运行 `sql/base.sql` 脚本获得基础的数据库结果，然后对 `platform_info` 表中的基本属性进行配置，每条记录都有功能描述。接下来介绍核心配置

最基础的记录： `qdd_phone`, `qdd_password`, `file_path`, `qxx_api`, `qxx_front`

项目在启动之后，会通过内置的规则，获取最新的抢多多地址，所以 `qdd_url` 可填可不填，随即通过 `qdd_phone`, `qdd_password` 获取到账号对应的 token，从而获取各种相关的信息并且同步至自己的服务器，同时会将核心的前端文件同步到服务器的 `file_path` 目录下，<b>切记该目录已存在</b>

`qxx_api`, `qxx_front` 则是抢夕夕的后端地址和前端地址，如果已经配置 Nginx 等代理之后，可以设置成相同的， 请正确填写该配置项

### 项目的基本配置

这里没什么好说的，只需要将配置文件中的数据库链接信息改成自己的就可以了，这里推荐 Redis 也设置上密码哦。同时设置一个自己喜欢的端口号和路径，然后就可以进行打包了。

### 支付宝付款配置

这里对接的是支付宝当面付扫码付款，手机可以直接唤醒支付宝的，但是和抢多多的移动web支付相比，体验上还是差了点，不过毕竟签约web的要求也更高嘛。

登录[支付宝开放平台](https://open.alipay.com/dev/workspace) 找到自己的应用，看一下有没有当面付的能力

![](https://raw.githubusercontent.com/zhx47/Pic-Go/master/20220106154803.png?token=AOTNIO3QHMWEU5DK2CIDTSLB22PPA)

如果有的话，恭喜你，可以将相关配置信息录入数据库中，即可食用

![](https://cdn.jsdelivr.net/gh/zhx47/Pic-Go/master/20220106155329.png?token=AOTNIO72GRYHRNZVGAS7S3DB22QDM)

关于应用密钥和支付宝公钥可以参考[官方文档](https://opendocs.alipay.com/mini/miniu/keytool/create) 进行配置

Enjoy it 😊