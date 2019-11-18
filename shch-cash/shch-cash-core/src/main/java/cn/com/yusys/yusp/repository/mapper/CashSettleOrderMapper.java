package cn.com.yusys.yusp.repository.mapper;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashSettleOrder;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CashSettleOrderMapper {
    int deleteByPrimaryKey(String serialNum);

    int insert(CashSettleOrder record);

    int insertSelective(CashSettleOrder record);

    CashSettleOrder selectByPrimaryKey(String serialNum);

    int updateByPrimaryKeySelective(CashSettleOrder record);

    int updateByPrimaryKey(CashSettleOrder record);

    List<CashSettleOrder> selectByModel(QueryModel model);

    int deleteByIds(String serialNums);
    
    int updateState(String serialNum);
    
    int updateStateReturn(String serialNum);
    
    int updateStateSuccess(String serialNum);
    
    int selectAccountState(@Param("cashAccount") String cashAccount, @Param("cashSettleAmt") String cashSettleAmt);
}