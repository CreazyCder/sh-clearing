package cn.com.yusys.yusp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

@RestController
@RequestMapping("/api/cash")
public class CashResource {
    private Logger logger = LoggerFactory.getLogger(CashResource.class);

    @GetMapping("/riqie")
    protected ResultDto<String> create(String code) {
    	logger.info("日切成功:"+code);
        return new ResultDto<String>("0");
    }
}