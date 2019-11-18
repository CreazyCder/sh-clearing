package cn.com.yusys.yusp.repository.mapper;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashAccountBalanceMapper {
    int deleteByPrimaryKey(@Param("memCode") String memCode, @Param("holderAccount") String holderAccount, @Param("titleCode") String titleCode, @Param("cashAccount") String cashAccount);

    int insert(CashAccountBalance record);

    int insertSelective(CashAccountBalance record);

    CashAccountBalance selectByPrimaryKey(@Param("memCode") String memCode, @Param("holderAccount") String holderAccount, @Param("titleCode") String titleCode, @Param("cashAccount") String cashAccount);

    int updateByPrimaryKeySelective(CashAccountBalance record);
    
    int updateMoney(CashAccountBalance record);

    int updateByPrimaryKey(CashAccountBalance record);

    List<CashAccountBalance> selectByModel(QueryModel model);

    int deleteByIds(String memCodes);
    
    int selectAccountState(CashAccountBalance record);
}