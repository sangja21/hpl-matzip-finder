package kr.hhplus.be.server.api.search;

import kr.hhplus.be.server.ServerApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = ServerApplication.class)
@AutoConfigureMockMvc
class SearchApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/search 요청 시 성공 응답을 반환한다")
    void searchRestaurantsApiTest() throws Exception {
        // given
        String keyword = "곱창";
        String location = "강남";

        // when & then
        mockMvc.perform(get("/api/search")
                        .param("keyword", keyword)
                        .param("location", location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data.items").isArray());
    }
}
