package cn.com.yusys.yusp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2019/11/23 16:16
 */
@Configuration
public class ClusterConfig {

    @Value("${renhang.gateway.cash.dev}")
    private String dev;

    @Value("${renhang.gateway.cash.pro}")
    private String pro;

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }
}
