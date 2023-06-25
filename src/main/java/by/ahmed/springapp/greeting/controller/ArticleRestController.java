package by.ahmed.springapp.greeting.controller;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping
    public Page<Article> getAll(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return articleService.getAll(offset, limit);
    }

    @GetMapping
    public Iterable<ArticleReadDto> mainArticle() {
        return articleService.findAll();
    }

    @PostMapping("/add/post")
    public ArticleReadDto addArticlePost(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "announce") String announce,
                                 @RequestParam(name = "fullText") String fullText,
                                 Model model) {
        var article = ArticleCreateEditDto.builder()
                .title(title)
                .announce(announce)
                .fullText(fullText)
                .date(Calendar.getInstance().getTime())
                .build();
        return articleService.create(article);
    }
}
