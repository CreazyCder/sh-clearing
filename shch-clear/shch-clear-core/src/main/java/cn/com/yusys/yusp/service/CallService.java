package cn.com.yusys.yusp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.commons.util.BeanUtil;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.SettleOrder;
/**
 * 调用服务
 * @author boip
 *
 */
@Service
public class CallService {
	@Autowired
	private CashClient cashClient;
	@Autowired
	private BondClient bondClient;
	/**
	 * 调用薄记结算请求
	 * @param order
	 * @return
	 */
    public ResultDto<String> callBondSettleApply(SettleOrder order){
    	BondDto req= new BondDto();
    	BeanUtil.beanCopy(order, req);
        return bondClient.procBond(req);
    }
	/**
	 * 调用资金结算请求
	 * @param order
	 * @return
	 */
    public ResultDto<String> callCashSettleApply(SettleOrder order){
    	CashDto req = new CashDto();
    	BeanUtil.beanCopy(order, req);
    	// 调用资金结算指令'
    	return cashClient.cash(req);
    }
    
    public String getBondStatus(String tradeId) {
    	return "";
    }
    public String getCashStatus(String tradeId) {
    	return "";
    }
}
