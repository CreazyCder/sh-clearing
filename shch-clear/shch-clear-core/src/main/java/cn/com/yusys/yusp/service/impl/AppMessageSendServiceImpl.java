package cn.com.yusys.yusp.service.impl;

import cn.com.yusys.yusp.message.client.MessageClient;
import cn.com.yusys.yusp.service.MessageSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 依赖应用组件app-message-client实现消息预警.
 *
 * @author wpplu
 * @since 2019/11/19
 */
@Component
public class AppMessageSendServiceImpl implements MessageSendService {
    private Logger log = LoggerFactory.getLogger(AppMessageSendServiceImpl.class);
    @Autowired
    private MessageClient messageClient;

    /**
     * 发送消息.
     * 指定异步任务执行器.
     *
     * @param content 请求内容.
     */
    @Override
    @Async("taskExecutor")
    public void sendMessage(Map<String, Object> content) {
        String result = messageClient.sendRealTimeMessageWithOutTemplate(content);
        log.info("添加消息到推送列表,结果:{}", result);
    }
}
