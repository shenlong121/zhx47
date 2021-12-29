package top.zhx47.qxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/8/14 15:38
 */
@SpringBootApplication
@EnableScheduling
public class QXXApplication {

    public static void main(String[] args) {
        SpringApplication.run(QXXApplication.class, args);
    }

}
