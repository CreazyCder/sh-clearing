package cn.com.yusys.yusys.utils.idempotent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yusys.utils.idempotent")
public class YusysIdempotentProperties {
    private  String ttl = "1d";

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
