package cn.com.yusys.yusp.web.rest;

import cn.com.yusys.yusp.ClearAppTest;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.domain.msg.dvpapply.DVPSettleApplyReq;
import cn.com.yusys.yusp.service.SettleOrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 清算单元测试接口.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public class SettleOrderResourceTest extends ClearAppTest {
    @Autowired
    private SettleOrderService orderService;

    /**
     * 测试查询.
     */
    @Test
    public void index() {
        QueryModel model = new QueryModel();
        model.setSize(10);
        log.info("查询结果:{}", orderService.selectPageByModel(model));
    }

    /**
     * 插入一个清算请求.
     */
    @Test
    public void save() {
        DVPSettleApplyReq req = new DVPSettleApplyReq();
       /* .builder()
                .tradeId("DVP201911170000001")
                .tradeDate("2019-11-17")
                .bondCode("011900001")
                .bondName("国债城建001")
                .bondType("01")
                .bondFaceAmt("999999999.99")
                .settleDate("2019-11-17")
                .settleType("01")
                .buyerMemCode("A000005")
                .buyerMemName("中国工商银行")
                .buyerHolderAccount("0000001")
                .buyerHolderAccountName("持有人1")
                .sellerMemCode("A000005")
                .sellerMemName("招商银行")
                .sellerHolderAccount("0000002")
                .sellerHolderAccountName("持有人2")
                .srcFrom("0")
                .build();*/
        log.info("保存成功:{}", orderService.insert(req));
    }
}