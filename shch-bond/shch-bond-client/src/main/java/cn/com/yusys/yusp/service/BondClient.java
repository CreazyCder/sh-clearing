package cn.com.yusys.yusp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

/**
 * 
 * @author Alice
 *
 */
@FeignClient(fallback=BondClientHystrix.class  ,name = "${service.feignclient.name.bond:shch-bond}")
public interface BondClient {
	
	/**
	 * 
	 * @param param
	 * @return  
	 */
    @RequestMapping(value = "/api/bondsettltorder/procBond/", method = RequestMethod.POST)
	public ResultDto<String> procBond(@RequestBody BondDto param);
}
