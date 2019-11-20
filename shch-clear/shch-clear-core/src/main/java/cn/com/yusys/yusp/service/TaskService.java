package cn.com.yusys.yusp.service;


import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 任务调用服务
 *
 * @author boip
 */
@Service
public class TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskService.class);

    public ResultDto<String> dayOff(String date) {
        log.info("接收到任务调度请求,日期:{}", date);
        return new ResultDto<String>(0, "清算处理成功", "info");
    }

}
