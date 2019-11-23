package cn.com.yusys.yusp.web.rest;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import cn.com.yusys.yusp.repository.mapper.CashAccountBalanceMapper;
import cn.com.yusys.yusp.service.CashDto;
import cn.com.yusys.yusp.service.CashSettleOrderService;
import cn.com.yusys.yusp.service.EnoughMoneyDto;
import cn.com.yusys.yusp.service.MoneyDto;
import cn.com.yusys.yusp.service.job.DataBackupJobImpl;
import cn.com.yusys.yusp.service.job.DataCopyJobImpl;
import cn.com.yusys.yusp.service.job.MoneyCheckJobImpl;

@RestController
@RequestMapping("/api/cash")
public class CashResource {
    private Logger logger = LoggerFactory.getLogger(CashResource.class);

    @Autowired
    private CashSettleOrderService cashSettleOrderService;
    
    @Autowired
    private CashAccountBalanceMapper cashAccountBalanceMapper;
    
    @Autowired
    private DataCopyJobImpl dbCody;
    @Autowired
   	private DataBackupJobImpl dbBackup;
    @Autowired
    private MoneyCheckJobImpl moneyChk;
    @PostMapping("/")
    protected ResultDto<String> create(@RequestBody @Valid CashDto record) {
    	logger.info("资金DVP结算请求指令接收报文:"+record);
    	cashSettleOrderService.asynUpdateCashCount(record);
        logger.info("资金DVP结算请求指令接收报文成功");
        return new ResultDto<>("0");
    }
    
    @PostMapping("/pay")
    protected ResultDto<String> pay(@RequestBody Map param) {
    	logger.info("支付应答报文:{}", param.toString());
		cashSettleOrderService.pay(param.get("serialNum").toString());
        return new ResultDto<>("0");
    }

    @GetMapping("/riqie")
    protected ResultDto<String> create(String code) {
    	logger.info("日切成功:"+code);
        return new ResultDto<>(0, "资金处理成功", "info");
    }
    
    /**
     * 通过Swagger发起人行来账报文到payback队列
     * @param enoughMoneyDto
     * @return
     * @throws Exception
     */
    @PostMapping("/enough_money")
    protected ResultDto<String> enoughMoney(@RequestBody EnoughMoneyDto enoughMoneyDto) throws Exception {
    	logger.info("到款数据:"+enoughMoneyDto);
    	cashSettleOrderService.enoughMoney(enoughMoneyDto);
        return new ResultDto<String>(0, "到款处理成功", "ok");
    }
    
    /**
     * 收到人行大额网关过来的往账应答112报文
     * @param moneyDto
     * @return
     */
    @PostMapping("/add")
    protected ResultDto<String> add(@RequestBody MoneyDto moneyDto) {
    	logger.info("pay:"+moneyDto);
    	CashAccountBalance record = new CashAccountBalance();
    	BeanUtils.copyProperties(moneyDto, record);
		cashAccountBalanceMapper.addMoney(record);
        return new ResultDto<String>("0");
    }
    
    @PostMapping("/dbcopy")
    protected ResultDto<String> copy() {
    	try {
			dbCody.execute(null);
		} catch (Exception e) {
			logger.error("copy error", e);
		}
        return new ResultDto<String>("0");
    }
    @PostMapping("/dbbackup")
    protected ResultDto<String> backup() {
    	try {
			dbBackup.execute(null);
		} catch (Exception e) {
			logger.error("backup error", e);
		}
        return new ResultDto<String>("0");
    }
    @PostMapping("/moneyChk")
    protected ResultDto<String> moneyChk() {
    	try {
    		moneyChk.execute(null);
		} catch (Exception e) {
			logger.error("moneyChk error", e);
		}
        return new ResultDto<String>("0");
    }
}