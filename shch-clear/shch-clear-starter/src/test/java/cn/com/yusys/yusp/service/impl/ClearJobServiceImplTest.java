package cn.com.yusys.yusp.service.impl;

import cn.com.yusys.yusp.ClearAppTest;
import cn.com.yusys.yusp.service.ClearJobService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时任务查询数据.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public class ClearJobServiceImplTest extends ClearAppTest {

    @Autowired
    private ClearJobService jobService;

    @Test
    public void findNeedBondSettles() {
        log.info("查询满足条件的数据:{}", jobService.findNeedBondSettles(100));
    }
}