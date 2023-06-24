package by.ahmed.springapp.controller;

import by.ahmed.springapp.annotation.IntegrationTesting;
import by.ahmed.springapp.dto.ArticleCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTesting
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class ArticleControllerTest {

    private final MockMvc mockMvc;

    @Test
    void addArticleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/article/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-article"));
    }

    @Test
    void addArticlePostTest() throws Exception {
        ArticleCreateEditDto articleCreateEditDto = new ArticleCreateEditDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/article/add/post")
                        .param("title", "TestTitle")
                        .param("announce", "TestAnnounce")
                        .param("fullText", "TestFullText")
                        .sessionAttr("articleCreateEditDto", articleCreateEditDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/article"));
    }
}
