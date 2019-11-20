package cn.com.yusys.yusp.service;


import java.math.BigDecimal;

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
    	req.setBondFaceAmt(order.getBondFaceAmt());
    	req.setCreditHolderAccount(order.getBuyerHolderAccount());
    	req.setCreditHolderAccountName(order.getBuyerHolderAccountName());
    	req.setCreditMemId(order.getBuyerMemCode());
    	req.setCreditMemName(order.getBuyerMemName());
    	req.setDebitHolderAccount(order.getSellerHolderAccount());
    	req.setDebitHolderAccountName(order.getSellerHolderAccountName());
    	req.setDebitMemId(order.getSellerMemCode());
    	req.setDebitMemName(order.getSellerMemName());
    	req.setBondDebitTitle("01");
    	req.setBondCreditTitle("02");
    	if("0".equals(order.getBondSettleStatus())|| "2".equals(order.getBondSettleStatus())) {
    		//应履行 等券
    		req.setOpertionType("1");//TODO
    	}else {
    		req.setOpertionType("2");//TODO
    	}
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
    	req.setTradeId(order.getTradeId());
    	req.setSettleOrderId(order.getSettleOrderId());
    	req.setCashSettleAmt(new BigDecimal(order.getSettleAmt()));
    	req.setCreditHolderAccount(order.getBuyerHolderAccount());
    	req.setCreditHolderAccountName(order.getBuyerHolderAccountName());
    	req.setCreditMemId(order.getBuyerMemCode());
    	req.setCreditMemName(order.getBuyerMemName());
    	req.setDebitHolderAccount(order.getSellerHolderAccount());
    	req.setDebitHolderAccountName(order.getSellerHolderAccountName());
    	req.setDebitMemId(order.getSellerMemCode());
    	req.setDebitMemName(order.getSellerMemName());
    	req.setCashDebitTitle("01");
    	req.setCashCreditTitle("02");
    	
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
