package by.ahmed.springapp.service;

import by.ahmed.springapp.annotation.IntegrationTesting;
import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@IntegrationTesting
@RequiredArgsConstructor
public class ArticleServiceTest {

    private final ArticleService articleService;
    private static final Long ARTICLE_ID = 1L;

//    @Test
//    public void findAllTest() {
//        var articles = articleService.findAll();
//        assertThat(articleListMapper.toArticles(dtoConverter.toCreateEditDtoList(articles))).hasSize(5);
//    }

    @Test
    public void findByIdTest() {
        var actualResult = articleService.findById(ARTICLE_ID);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(actual -> assertEquals(ARTICLE_ID, actual.getId()));
    }

    @Test
    public void createTest() {
        var expectedResult = buildDto("Название", "Анонс", "Текст",
                LocalDate.now());
        var actualResult = articleService.create(expectedResult);

        assertEquals(expectedResult.getTitle(), actualResult.getTitle());
        assertEquals(expectedResult.getAnnounce(), actualResult.getAnnounce());
        assertEquals(expectedResult.getFullText(), actualResult.getFullText());
        assertEquals(expectedResult.getDate(), actualResult.getDate());
    }

    @Test
    public void updateTest() {
        ArticleCreateEditDto articleDto = buildDto("Название1", "Анонс1", "Текст1",
                LocalDate.of(2019, 1, 1));
        Optional<ArticleReadDto> actualResult = articleService.update(ARTICLE_ID, articleDto);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(article -> {
            assertEquals(articleDto.getTitle(), article.getTitle());
            assertEquals(articleDto.getAnnounce(), article.getAnnounce());
            assertEquals(articleDto.getFullText(), article.getFullText());
            assertEquals(articleDto.getDate(), article.getDate());
        });
    }

    @Test
    public void deleteTest() {
        assertFalse(articleService.delete(-124L));
        assertTrue(articleService.delete(ARTICLE_ID));
    }

    private ArticleCreateEditDto buildDto(String title,
                                          String announce,
                                          String fullText,
                                          LocalDate date) {
        return ArticleCreateEditDto.builder()
                .title(title)
                .announce(announce)
                .fullText(fullText)
                .date(date)
                .build();
    }
}
