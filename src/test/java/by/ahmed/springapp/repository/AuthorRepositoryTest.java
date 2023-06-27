package by.ahmed.springapp.repository;

import by.ahmed.springapp.annotation.IntegrationTesting;
import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.entity.Authentication;
import by.ahmed.springapp.entity.Author;
import by.ahmed.springapp.entity.Gender;
import by.ahmed.springapp.mapper.ArticleMapper;
import by.ahmed.springapp.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

@IntegrationTesting
@RequiredArgsConstructor
public class AuthorRepositoryTest {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final ArticleMapper articleMapper;

    @Test
    public void findAuthorsByFLTest() {
        var expected = AuthorCreateEditDto.builder()
                .first_name("Георгий")
                .last_name("Дильман")
                .birth_date(new Date(2002, Calendar.NOVEMBER, 1))
                .gender(Gender.MALE)
                .job_title("Senior Java Developer")
                .build();
        authorRepository.save(authorMapper.toAuthor(expected));
        List<Author> actualList = authorRepository.findAuthorsByFL(expected.getFirst_name(), expected.getLast_name());
        actualList.forEach(actual -> {
                    assertEquals(expected.getFirst_name(), actual.getFirst_name());
                    assertEquals(expected.getLast_name(), actual.getLast_name());
                });
        authorRepository.delete(authorMapper.toAuthor(expected));
    }

    @Test
    public void findArticlesOfAuthorByEmailTest() {
        List<Article> expectedList = new ArrayList<>();
        var author = AuthorCreateEditDto.builder()
                .first_name("Георгий")
                .last_name("Дильман")
                .birth_date(new Date(2002, Calendar.NOVEMBER, 1))
                .gender(Gender.MALE)
                .job_title("Senior Java Developer")
                .build();
        var authentication = new Authentication(authorMapper.toAuthor(author), "ahmedtretiy@gmail.com", "&#@nb123");
        author.setAuthentication(authentication);
        var article1 = ArticleCreateEditDto.builder()
                .title("Название1")
                .announce("Анонс1")
                .date(Calendar.getInstance().getTime())
                .author(authorMapper.toAuthor(author))
                .build();
        expectedList.add(articleMapper.toArticle(article1));
        var article2 = ArticleCreateEditDto.builder()
                .title("Название2")
                .announce("Анонс2")
                .date(Calendar.getInstance().getTime())
                .author(authorMapper.toAuthor(author))
                .build();
        expectedList.add(articleMapper.toArticle(article2));
        author.setArticles(expectedList);
        authorRepository.save(authorMapper.toAuthor(author));
        List<Article> actualLst = authorRepository.findArticlesOfAuthorByEmail(author.getAuthentication().getEmail());
        assertEquals(expectedList.size(), actualLst.size());
    }
}
