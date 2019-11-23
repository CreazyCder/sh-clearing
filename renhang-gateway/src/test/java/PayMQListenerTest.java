import cn.com.yusys.yusp.RenhangGatewayMicroserviceApp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RenhangGatewayMicroserviceApp.class})
public class PayMQListenerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDispatch() throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("test", "test");
        data.put("serialNum", "0001");

        String url = "http://192.168.74.192:18001/api/cash/pay";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());

        String msgJson = objectMapper.writeValueAsString(data);

        HttpEntity<String> formEntity = new HttpEntity<>(msgJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.postForObject(url, formEntity, Object.class);
        System.out.println("服务cash返回应答" + result);
    }
}
