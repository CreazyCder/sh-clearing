package cn.com.yusys.yusp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.util.BeanUtil;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.SettleOrder;

@Service
public class CallService {
	@Autowired
	private CashClient cashClient;
	@Autowired
	private BondClient bondClient;
	
    public ResultDto<String> callBondSettleApply(SettleOrder order){
    	BondDto req= new BondDto();
    	BeanUtil.beanCopy(order, req);
        return bondClient.procBond(req);
    }

    public ResultDto<String> callCashSettleApply(SettleOrder order){
    	CashDto req = new CashDto();
    	BeanUtil.beanCopy(order, req);
    	// 调用资金结算指令'
    	return cashClient.cash(req);
    }
}
