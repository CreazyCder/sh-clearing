package cn.com.yusys.yusp.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.service.CashDto;
import cn.com.yusys.yusp.service.CashSettleOrderService;

@RestController
@RequestMapping("/api/cash")
public class CashResource {
    private Logger logger = LoggerFactory.getLogger(CashResource.class);

    @Autowired
    private CashSettleOrderService cashSettleOrderService;
   
    @PostMapping("/")
    protected ResultDto<String> create(@RequestBody @Valid CashDto record) {
    	logger.debug("资金DVP结算请求指令接收报文:"+record);
    	cashSettleOrderService.asynUpdateCashCount(record);
        logger.debug("资金DVP结算请求指令接收报文成功");
        return new ResultDto<String>("0");
    }
}