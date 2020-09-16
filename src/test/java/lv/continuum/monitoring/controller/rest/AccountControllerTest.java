package lv.continuum.monitoring.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAccountDto() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test"));
    }

    @Test
    void getAccountDtoNotFound() throws Exception {

        // Error models cannot be tested due to MockMvc limitations
        // https://github.com/spring-projects/spring-boot/issues/5574
        mockMvc.perform(get("/accounts/150"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAccountDtoBadRequest() throws Exception {
        mockMvc.perform(get("/accounts/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void saveAccountDto() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"username": "new"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.username").value("new"));
    }

    @Test
    void saveAccountDtoUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/accounts")
                .content("""
                        {"username": "new"}""
                        """))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void saveAccountDtoBadRequest() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"username": "!!!"}
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRecordDtosByAccountId() throws Exception {
        mockMvc.perform(get("/accounts/1/records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(content().json("""
                        [{
                          "recordType": "LOGOUT",
                          "createdAt": "2018-01-03T09:30:45.000+0000"
                        }, {
                          "recordType": "LOGIN",
                          "createdAt": "2018-01-02T18:08:08.000+0000"
                        }]
                        """));
    }

    @Test
    void getRecordDtosByAccountIdNotFound() throws Exception {
        mockMvc.perform(get("/accounts/150/records"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRecordDtosByAccountIdBadRequest() throws Exception {
        mockMvc.perform(get("/accounts/abc/records"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    void saveRecordDto() throws Exception {
        mockMvc.perform(post("/accounts/1/records")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"recordType": "LOGIN"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.recordType").value("LOGIN"))
                .andExpect(jsonPath("$.createdAt").isString());
    }

    @Test
    void saveRecordDtoUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/accounts/1/records")
                .content("""
                        {"recordType": "LOGOUT"}
                        """))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void saveRecordDtoBadRequest() {
        String[] urlTemplates = {
                "/accounts/abc/records",
                "/accounts/1/records",
                "/accounts/1/records"
        };
        String[] contents = {
                """
                {"recordType": "LOGIN"}
                """,
                """
                {"recordType": "WRONG"}
                """,
                "!!!"
        };
        IntStream.range(0, urlTemplates.length).forEach(i -> {
            try {
                mockMvc.perform(post(urlTemplates[i])
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contents[i]))
                        .andExpect(status().isBadRequest());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
