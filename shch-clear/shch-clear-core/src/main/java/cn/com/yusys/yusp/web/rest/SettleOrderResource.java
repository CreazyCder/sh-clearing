/*
 * 代码生成器自动生成的
 * Since 2008 - 2019
 *
 */
package cn.com.yusys.yusp.web.rest;

import cn.com.yusys.yusp.commons.exception.YuspException;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.domain.SettleOrder;
import cn.com.yusys.yusp.domain.msg.dvpapply.DVPSettleApplyReq;
import cn.com.yusys.yusp.service.SettleOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @version 1.0.0
 * @项目名称: shch-clear-core模块
 * @类名称: SettleOrderResource
 * @类描述: #资源类
 * @功能描述:
 * @创建人: boip
 * @创建时间: 2019-11-18 11:32:23
 * @修改备注:
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @Copyright (c) 宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/settleorder")
public class SettleOrderResource {
    private Logger logger = LoggerFactory.getLogger(SettleOrderResource.class);
    @Autowired
    private SettleOrderService settleOrderService;

    /**
     * 分页查询接口.
     *
     * @param queryModel 查询对象.其中condition为参数条件.
     * @return 分页的清算列表.
     */
    @GetMapping("/")
    protected ResultDto<List<SettleOrder>> index(QueryModel queryModel) {
        logger.debug("根据条件查询用户列表:{}", queryModel);
        List<SettleOrder> list = settleOrderService.selectPageByModel(queryModel);
        return new ResultDto<>(list);
    }

    /**
     * 录入清算指令.
     *
     * @param applyReq 待录入的清算指令部分.
     * @return 录入完成的记录.
     */
    @PostMapping("/")
    protected ResultDto<String> save(@Validated @RequestBody DVPSettleApplyReq applyReq) {
        int order = settleOrderService.insert(applyReq);
        if (order == 0) {
            throw new YuspException("clear_01", "插入待清算列表失败!");
        } else {
            return new ResultDto<>("更新" + order + "条数据!");
        }
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
}
