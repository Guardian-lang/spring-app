package by.ahmed.springapp.http.controller.rest;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping(value = "/all", params = {"offset", "limit"})
    public Page<Article> getAll(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return articleService.getAll(offset, limit);
    }

    @GetMapping(value = "/main")
    public Iterable<ArticleReadDto> mainArticle() {
        return articleService.findAll();
    }

    @PostMapping(value = "/add/post", params = {"title", "announce", "fullText"})
    public ArticleReadDto addArticlePost(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "announce") String announce,
                                 @RequestParam(name = "fullText") String fullText) {
        var article = ArticleCreateEditDto.builder()
                .title(title)
                .announce(announce)
                .fullText(fullText)
                .date(LocalDate.now())
                .build();
        return articleService.create(article);
    }
}
