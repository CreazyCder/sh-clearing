package cn.com.yusys.yusp.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

/**
 * 任务调用
 */
@FeignClient(name = "${application.service.shch-clear:shch-clear}", fallback = TaskClient.class)
public interface TaskClient {
    /**
     * 薄记异步结算通知
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/task/dayOff/{bizDate}")
    ResultDto<String> dayOff(@PathVariable("bizDate") String date);
}