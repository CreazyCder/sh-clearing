package cn.com.yusys.yusp.service;


import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
/**
 * 任务调用服务
 * @author boip
 *
 */
@Slf4j
@Service
public class TaskService {
	
    public ResultDto<String> dayOff(String date){
    	log.info("接收到任务调度请求,日期:{}",date);
    	return new ResultDto<String>(0, "处理成功", "info");
    }

}
