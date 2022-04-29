package net.schoolvery.schoolveryserver.UserTest;

import net.schoolvery.schoolveryserver.SpringTestSupport;
import net.schoolvery.schoolveryserver.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("User API 테스트")
public class UserAPITest extends SpringTestSupport {

    @DisplayName("User Create Test")
    @Test
    void 유저생성() throws Exception {
        String user = mapper.writeValueAsString(new User(UUID.randomUUID(), "김의빈", "성결대학교",
                "Joe", "asdo@naver.com", "asdas123", "asdffadf", 13513, 0104242,
                "asdasd"));

        mockMvc.perform(
                        post("/api/v1/users/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .content(user)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("Read All Users")
    @Test
    public void 전체유저읽기() throws Exception {
        mockMvc.perform(
                        get("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

//    @DisplayName("Update Users")
//    @Test
//    void 유저_업데이트() throws Exception {
//        UUID id = UUID.randomUUID();
//        String user = mapper.writeValueAsString(new User(id, "김의빈2", "성결대학교2",
//                "Joe2", "asdo@naver.com2", "asdas1232", "asdffadf2", 135132, 01042423,
//                "asdasd2"));
//
//        mockMvc.perform(
//                        patch("/api/v1/{id}", id)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(user)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//    }







}
