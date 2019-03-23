package lv.continuum.monitoring.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAccountDto() throws Exception {
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test"));
    }

    @Test
    public void testGetAccountDtoNotFound() throws Exception {

        // Error models cannot be tested due to MockMvc limitations
        // https://github.com/spring-projects/spring-boot/issues/5574
        mockMvc.perform(get("/accounts/150"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAccountDtoBadRequest() throws Exception {
        mockMvc.perform(get("/accounts/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveAccountDto() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"new\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.username").value("new"));
    }

    @Test
    public void testSaveAccountDtoUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/accounts")
                .content("{\"username\":\"new\"}"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testSaveAccountDtoBadRequest() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"!!!\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRecordDtosByAccountId() throws Exception {
        mockMvc.perform(get("/accounts/1/records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(content().json(
                        "[{\"recordType\":\"LOGOUT\",\"createdAt\":\"2018-01-03T09:30:45.000+0000\"}," +
                                "{\"recordType\":\"LOGIN\",\"createdAt\":\"2018-01-02T18:08:08.000+0000\"}]"));
    }

    @Test
    public void testGetRecordDtosByAccountIdNotFound() throws Exception {
        mockMvc.perform(get("/accounts/150/records"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRecordDtosByAccountIdBadRequest() throws Exception {
        mockMvc.perform(get("/accounts/abc/records"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveRecordDto() throws Exception {
        mockMvc.perform(post("/accounts/1/records")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"recordType\":\"LOGIN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.recordType").value("LOGIN"))
                .andExpect(jsonPath("$.createdAt").isString());
    }

    @Test
    public void testSaveRecordDtoUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/accounts/1/records")
                .content("{\"recordType\":\"LOGOUT\"}"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testSaveRecordDtoBadRequest() throws Exception {
        String[] urlTemplates = {
                "/accounts/abc/records",
                "/accounts/1/records",
                "/accounts/1/records"};
        String[] contents = {
                "{\"recordType\":\"LOGIN\"}",
                "{\"recordType\":\"WRONG\"}",
                "!!!"};

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
