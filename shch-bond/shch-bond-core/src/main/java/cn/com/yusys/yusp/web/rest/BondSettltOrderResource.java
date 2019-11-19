/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.BondSettltOrder;
import cn.com.yusys.yusp.service.BondDto;
import cn.com.yusys.yusp.service.BondSettltOrderService;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondSettltOrderResource
 * @类描述: #资源类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:02
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/bondsettltorder")
public class BondSettltOrderResource {
	private Logger logger = LoggerFactory.getLogger(BondSettltOrderResource.class);
    @Autowired
    private BondSettltOrderService bondSettltOrderService;
    
    /**
     * @函数名称:procBond
     * @函数描述:簿记账务处理，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/procBond")
    protected ResultDto<String> procBond(@RequestBody BondDto bondDto) throws URISyntaxException {
    	logger.debug("簿记DVP结算指令处理开始:"+bondDto);
    	//根据bondDto的opertionType进行分类处理簿记处理流程（异步处理）
    	//1圈券逻辑；
    	if("1".equals(bondDto.getOpertionType())) {
    		logger.debug("簿记DVP结算圈券指令接收报文:"+bondDto);
    		bondSettltOrderService.procCouponBond(bondDto);
    		logger.debug("资金DVP结算请求指令接收报文成功");
    	}
    	//2记账逻辑；
    	if("2".equals(bondDto.getOpertionType())) {
    		logger.debug("簿记DVP结算记账指令接收报文:"+bondDto);
    		bondSettltOrderService.procRecordBond(bondDto);
    		logger.debug("资金DVP结算记账指令接收报文成功");
    	}
    	//3撤销逻辑;
    	if("3".equals(bondDto.getOpertionType())) {
    		logger.debug("簿记DVP结算撤销指令接收报文:"+bondDto);
    		bondSettltOrderService.procCancleBond(bondDto);
    		logger.debug("资金DVP结算撤销指令接收报文成功");
    	}
    	
    	logger.debug("簿记DVP结算指令处理结束");
        return new ResultDto<String>("0");
        
    }


	/**
     * 全表查询.
     * 
     * @param id
     * @return
     */
    @GetMapping("/query/all")
    protected ResultDto<List<BondSettltOrder>> query() {
        QueryModel queryModel = new QueryModel();
        List<BondSettltOrder> list = bondSettltOrderService.selectAll(queryModel);
        return new ResultDto<List<BondSettltOrder>>(list);
    }
	
    /**
     * @函数名称:index
     * @函数描述:查询对象列表，公共API接口
     * @参数与返回说明:
     * @param QueryModel
     *            分页查询类
     * @算法描述:
     */
    @GetMapping("/")
    protected ResultDto<List<BondSettltOrder>> index(QueryModel queryModel) {
        List<BondSettltOrder> list = bondSettltOrderService.selectByModel(queryModel);
        return new ResultDto<List<BondSettltOrder>>(list);
    }

    /**
     * @函数名称:show
     * @函数描述:查询单个对象，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @GetMapping("/{serialNum}")
    protected ResultDto<BondSettltOrder> show(@PathVariable("serialNum") String serialNum) {
        BondSettltOrder bondSettltOrder = bondSettltOrderService.selectByPrimaryKey(serialNum);
        return new ResultDto<BondSettltOrder>(bondSettltOrder);
    }

    /**
     * @函数名称:create
     * @函数描述:实体类创建，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/")
    protected ResultDto<BondSettltOrder> create(@RequestBody BondSettltOrder bondSettltOrder) throws URISyntaxException {
        bondSettltOrderService.insert(bondSettltOrder);
        return new ResultDto<BondSettltOrder>(bondSettltOrder);
    }

    /**
     * @函数名称:update
     * @函数描述:对象修改，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/update")
    protected ResultDto<Integer> update(@RequestBody BondSettltOrder bondSettltOrder) throws URISyntaxException {
        int result = bondSettltOrderService.update(bondSettltOrder);
        return new ResultDto<Integer>(result);
    }


    /**
     * @函数名称:delete
     * @函数描述:单个对象删除，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/delete/{serialNum}")
    protected ResultDto<Integer> delete(@PathVariable("serialNum") String serialNum) {
        int result = bondSettltOrderService.deleteByPrimaryKey(serialNum);
        return new ResultDto<Integer>(result);
    }

    /**
     * @函数名称:batchdelete
     * @函数描述:批量对象删除，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/batchdelete/{ids}")
    protected ResultDto<Integer> deletes(@PathVariable String ids) {
        int result = bondSettltOrderService.deleteByIds(ids);
        return new ResultDto<Integer>(result);
    }
}
