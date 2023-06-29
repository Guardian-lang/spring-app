package by.ahmed.springapp.repository;

import by.ahmed.springapp.annotation.IntegrationTesting;
import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTesting
@RequiredArgsConstructor
public class ArticleRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Test
    public void findByTitleTest() {
        var expectedValue = buildArticle("Название",
                "Анонс",
                "Текст", LocalDate.now());
        articleRepository.save(expectedValue);
        Article actualValue = articleRepository.findByTitle("Название");
        assertEquals(expectedValue, actualValue);
        articleRepository.delete(expectedValue);
    }

    @Test
    public void sortByDateTest() {
        List<Article> expectedArticleList = new ArrayList<>();
        var article1 = buildArticle("Название1",
                "Анонс1",
                "Текст1", LocalDate.of(2019, 1, 1));
        articleRepository.save(article1);
        expectedArticleList.add(article1);
        var article2 = buildArticle("Название2",
                "Анонс2",
                "Текст2", LocalDate.of(2022, 1, 1));
        articleRepository.save(article2);
        expectedArticleList.add(article1);
        List<Article> actualArticleList = articleRepository.sortByDate(LocalDate.of(2018, 1, 1),
                LocalDate.of(2023, 1, 1));
        assertEquals(expectedArticleList, actualArticleList);
        articleRepository.delete(article1);
        articleRepository.delete(article2);
    }

    @Test
    public void findByIdTest() {
        var article = articleRepository.findById(1L);
        assertThat(article.orElseThrow()).hasNoNullFieldsOrProperties();
    }

//    @Test
//    public void findFirstNArticlesTest() {
//
//    }

    private Article buildArticle(String title, String announce, String fullText, LocalDate date) {
        return articleMapper.toArticle(ArticleCreateEditDto.builder()
                .title(title)
                .announce(announce)
                .fullText(fullText)
                .date(date)
                .build());
    }
}
