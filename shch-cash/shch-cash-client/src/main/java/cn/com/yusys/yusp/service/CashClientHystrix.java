package cn.com.yusys.yusp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

@Component
public class CashClientHystrix implements  CashClient {
    
	private final Logger log = LoggerFactory.getLogger(CashClientHystrix.class);

	@Override
	public ResultDto<String> cash(CashDto param) {
		log.error("通讯超时触发熔断："+param);
		ResultDto<String> data = new ResultDto<String>();
		data.setCode(2);
		data.setMessage("通讯异常");
		return null;
	}

}
