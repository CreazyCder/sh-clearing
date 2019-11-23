package cn.com.yusys.yusp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2019/11/23 16:16
 */
@Configuration
public class EnvClusterConfig {

    @Value("${env}")
    private String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
