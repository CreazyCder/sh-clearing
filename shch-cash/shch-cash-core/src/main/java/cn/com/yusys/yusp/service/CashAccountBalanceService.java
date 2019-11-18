package cn.com.yusys.yusp.service;

import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.CashAccountBalance;
import cn.com.yusys.yusp.repository.mapper.CashAccountBalanceMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CashAccountBalanceService {
    @Autowired
    private CashAccountBalanceMapper cashAccountBalanceMapper;

    public int deleteByPrimaryKey(String memCode, String holderAccount, String titleCode, String cashAccount) {
        return cashAccountBalanceMapper.deleteByPrimaryKey(memCode,holderAccount,titleCode,cashAccount);
    }

    public int insert(CashAccountBalance record) {
        return cashAccountBalanceMapper.insert(record);
    }

    public int insertSelective(CashAccountBalance record) {
        return cashAccountBalanceMapper.insertSelective(record);
    }

    public CashAccountBalance selectByPrimaryKey(String memCode, String holderAccount, String titleCode, String cashAccount) {
        return cashAccountBalanceMapper.selectByPrimaryKey(memCode,holderAccount,titleCode,cashAccount);
    }

    public int selectAccountState(CashAccountBalance record) {
        return cashAccountBalanceMapper.selectAccountState(record);
    }
    
    public int updateByPrimaryKeySelective(CashAccountBalance record) {
        return cashAccountBalanceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CashAccountBalance record) {
        return cashAccountBalanceMapper.updateByPrimaryKey(record);
    }

    public List<CashAccountBalance> selectByModel(QueryModel model) {
        PageHelper.startPage(model.getPage(), model.getSize());
        List<CashAccountBalance> list = cashAccountBalanceMapper.selectByModel(model);
        PageHelper.clearPage();
        return list;
    }

    public int deleteByIds(String memCodes) {
        return cashAccountBalanceMapper.deleteByIds(memCodes);
    }
}