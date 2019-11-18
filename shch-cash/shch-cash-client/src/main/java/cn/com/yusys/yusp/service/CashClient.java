package cn.com.yusys.yusp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

/**
 * 
 * @author figue
 *
 */
@FeignClient(fallback=CashClientHystrix.class  ,name = "${service.feignclient.name.cash:shch-cash}")
public interface CashClient {
	
	/**
	 * 
	 * @param param
	 * @return  
	 */
    @RequestMapping(value = "/api/cash/", method = RequestMethod.POST)
	public ResultDto<String> cash(@RequestBody CashDto param);
}
