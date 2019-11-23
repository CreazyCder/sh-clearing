
import cn.com.yusys.yusp.service.job.DataCopyJobImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class DataCopyJobImplTest{

    @Test
    public void testExecute() {
        DataCopyJobImpl dataCopyJob = new DataCopyJobImpl();
        try {
            dataCopyJob.execute("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}