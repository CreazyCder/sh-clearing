package cn.com.yusys.yusp.service;

import java.util.Map;

/**
 * 消息发送服务.
 *
 * @author wpplu
 * @since 2019/11/19
 */
public interface MessageSendService {
    /**
     * 发送消息.
     *
     * @param content 请求内容.
     */
    void sendMessage(Map<String, Object> content);
}
