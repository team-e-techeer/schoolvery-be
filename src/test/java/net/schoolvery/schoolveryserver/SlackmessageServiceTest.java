package net.schoolvery.schoolveryserver;

import net.schoolvery.schoolveryserver.global.config.SlackmessageConfig;
import org.junit.jupiter.api.Test;

public class SlackmessageServiceTest {

    @Test
    void Send_message() {
        SlackmessageConfig.send("현재 EC2 정상작동 중 http://52.79.69.132/swagger-ui/index.html#/ 스웨거 참조 + http://52.79.69.132:8082 그라파나 모니터링 확인가능!" );

    }
}
