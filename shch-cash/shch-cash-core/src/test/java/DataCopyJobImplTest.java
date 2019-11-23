
import cn.com.yusys.yusp.service.job.DataCopyJobImpl;
import org.junit.Test;

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
