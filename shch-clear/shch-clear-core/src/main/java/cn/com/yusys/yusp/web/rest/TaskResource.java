package cn.com.yusys.yusp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.service.TaskService;
/**
 * 提供任务调用服务
 * @author boip
 *
 */
@RequestMapping("/api/task/")
public class TaskResource {
    @Autowired
    private TaskService taskServ;
    /**
     * 日终处理
     * @return
     */
    @GetMapping("/dayOff/{bizDate}")
    protected ResultDto<String> dayOff(@PathVariable("bizDate") String date) {
        return taskServ.dayOff(date);
    }
 
}
