package cn.com.yusys.yusp;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 全局测试.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShchClearApp.class})
public class ClearAppTest {
    protected Logger log = LoggerFactory.getLogger(ClearAppTest.class);

}
