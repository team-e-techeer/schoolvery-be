package net.schoolvery.schoolveryserver;

import net.schoolvery.schoolveryserver.global.config.SlackmessageConfig;
import org.junit.jupiter.api.Test;

public class SlackmessageServiceTest {

    @Test
    void Send_message() {
        SlackmessageConfig.send("Test Message");
    }
}
