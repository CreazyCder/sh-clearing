package cn.com.yusys.yusp.service;

import org.springframework.stereotype.Component;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

@Component
public class TaskClientFallback implements TaskClient {

	@Override
	public ResultDto<String> dayOff(String date) {
		System.out.println("error tasks");
		return new ResultDto<String>(1,"fail","error");
	}

    
}
