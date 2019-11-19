/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.SettleOrder;
import cn.com.yusys.yusp.service.SettleOrderService;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: SettleOrderResource
 * @类描述: #资源类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:24:54
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/settleorder")
public class SettleOrderResource {
    @Autowired
    private SettleOrderService settleOrderService;
    
	/**
     * 全表查询.
     * 
     * @param id
     * @return
     */
    @GetMapping("/query/all")
    protected ResultDto<List<SettleOrder>> query() {
        QueryModel queryModel = new QueryModel();
        List<SettleOrder> list = settleOrderService.selectAll(queryModel);
        return new ResultDto<List<SettleOrder>>(list);
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
    protected ResultDto<List<SettleOrder>> index(QueryModel queryModel) {
        List<SettleOrder> list = settleOrderService.selectByModel(queryModel);
        return new ResultDto<List<SettleOrder>>(list);
    }

    /**
     * @函数名称:show
     * @函数描述:查询单个对象，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @GetMapping("/{settleOrderId}")
    protected ResultDto<SettleOrder> show(@PathVariable("settleOrderId") String settleOrderId) {
        SettleOrder settleOrder = settleOrderService.selectByPrimaryKey(settleOrderId);
        return new ResultDto<SettleOrder>(settleOrder);
    }

    /**
     * @函数名称:create
     * @函数描述:实体类创建，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/")
    protected ResultDto<SettleOrder> create(@RequestBody SettleOrder settleOrder) throws URISyntaxException {
        settleOrderService.insert(settleOrder);
        return new ResultDto<SettleOrder>(settleOrder);
    }

    /**
     * @函数名称:update
     * @函数描述:对象修改，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/update")
    protected ResultDto<Integer> update(@RequestBody SettleOrder settleOrder) throws URISyntaxException {
        int result = settleOrderService.update(settleOrder);
        return new ResultDto<Integer>(result);
    }


    /**
     * @函数名称:delete
     * @函数描述:单个对象删除，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/delete/{settleOrderId}")
    protected ResultDto<Integer> delete(@PathVariable("settleOrderId") String settleOrderId) {
        int result = settleOrderService.deleteByPrimaryKey(settleOrderId);
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
        int result = settleOrderService.deleteByIds(ids);
        return new ResultDto<Integer>(result);
    }
}
