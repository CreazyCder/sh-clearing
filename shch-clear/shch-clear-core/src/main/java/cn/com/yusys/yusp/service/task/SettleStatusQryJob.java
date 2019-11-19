package cn.com.yusys.yusp.service.task;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.job.core.handler.IJobHandler;
import cn.com.yusys.yusp.repository.mapper.SettleOrderMapper;
import cn.com.yusys.yusp.service.CallService;
/**
 * 状态查证任务
 * @author boip
 *
 */
public class SettleStatusQryJob extends IJobHandler {
	@Autowired
	private CallService call;
	@Autowired
	private SettleOrderMapper mapper;
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		// 查询交割中处理数据。
		
		mapper.selectDeliveringByStatus(100);
		
		return null;
	}

}
