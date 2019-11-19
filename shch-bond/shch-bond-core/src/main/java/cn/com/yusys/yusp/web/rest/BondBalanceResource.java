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
import cn.com.yusys.yusp.domain.BondBalance;
import cn.com.yusys.yusp.service.BondBalanceService;

/**
 * @项目名称: shch-bond-core模块
 * @类名称: BondBalanceResource
 * @类描述: #资源类
 * @功能描述: 
 * @创建人: Alice
 * @创建时间: 2019-11-18 21:23:21
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/bondbalance")
public class BondBalanceResource {
    @Autowired
    private BondBalanceService bondBalanceService;

	/**
     * 全表查询.
     * 
     * @param id
     * @return
     */
    @GetMapping("/query/all")
    protected ResultDto<List<BondBalance>> query() {
        QueryModel queryModel = new QueryModel();
        List<BondBalance> list = bondBalanceService.selectAll(queryModel);
        return new ResultDto<List<BondBalance>>(list);
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
    protected ResultDto<List<BondBalance>> index(QueryModel queryModel) {
        List<BondBalance> list = bondBalanceService.selectByModel(queryModel);
        return new ResultDto<List<BondBalance>>(list);
    }


    /**
     * @函数名称:create
     * @函数描述:实体类创建，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/")
    protected ResultDto<BondBalance> create(@RequestBody BondBalance bondBalance) throws URISyntaxException {
        bondBalanceService.insert(bondBalance);
        return new ResultDto<BondBalance>(bondBalance);
    }

    /**
     * @函数名称:update
     * @函数描述:对象修改，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/update")
    protected ResultDto<Integer> update(@RequestBody BondBalance bondBalance) throws URISyntaxException {
        int result = bondBalanceService.update(bondBalance);
        return new ResultDto<Integer>(result);
    }

    /**
     * @函数名称:delete
     * @函数描述:单个对象删除，公共API接口
     * @参数与返回说明:
     * @算法描述:
     */
    @PostMapping("/delete")
    protected ResultDto<Integer> delete(String memCode, String holderAccount, String bondCode, String titleCode) {
        int result = bondBalanceService.deleteByPrimaryKey(memCode, holderAccount, bondCode, titleCode);
        return new ResultDto<Integer>(result);
    }

}
