package cn.com.yusys.yusp.service;


import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CallService {
	@Autowired
	private CashClient client;
	
	
    public ResultDto callBondSettleApply(){
    	
        return null;
    }

    public ResultDto<String> callCashSettleApply(CashDto req){
    	// 调用资金结算指令
    	return client.cash(req);
    }
}
