package net.schoolvery.schoolveryserver.UserTest;

import net.schoolvery.schoolveryserver.global.utils.AES128;
import org.junit.jupiter.api.Test;


public class passwordTest {

    @Test
    public void 비밀번호_AES체크() throws Exception{
        AES128 aes128 = new AES128("password123214214");
        String enc = aes128.encrypt("this is plain text");
        String dec = aes128.decrypt(enc);

        System.out.printf("enc  : "+ enc);
        System.out.printf("dec  : " + dec);
    }

}
