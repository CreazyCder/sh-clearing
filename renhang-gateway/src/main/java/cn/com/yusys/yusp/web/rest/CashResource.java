package cn.com.yusys.yusp.web.rest;

import cn.com.yusys.yusp.commons.exception.YuspException;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.service.EnoughMoneyDto;
import cn.com.yusys.yusp.service.MoneyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/gw")
public class CashResource {
    private Logger logger = LoggerFactory.getLogger(CashResource.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 通过Swagger发起人行来账报文到payback队列
     * @param enoughMoneyDto
     * @return
     * @throws Exception
     */
    @PostMapping("/enough_money")
    protected ResultDto<String> enoughMoney(@RequestBody EnoughMoneyDto enoughMoneyDto) throws Exception {
        try {
            String requestString = objectMapper.writeValueAsString(enoughMoneyDto);
            logger.info("发送到款数据到Rabbit:" + requestString);
            amqpTemplate.convertAndSend("to_hvps", requestString);
        } catch (Exception e) {
            throw new YuspException("1", "发送rabbitmq失败" + e.getMessage());
        }
        return new ResultDto<String>(0, "到款处理成功", "ok");
    }
}
