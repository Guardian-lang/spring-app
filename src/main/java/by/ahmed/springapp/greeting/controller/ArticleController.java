package by.ahmed.springapp.greeting.controller;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.dto.ArticleReadDto;
import by.ahmed.springapp.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public String mainArticle(Model model) {
        Iterable<ArticleReadDto> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article";
    }

    @GetMapping("/article/add")
    public String addArticle(Model model) {
        return "add-article";
    }

    @PostMapping("/article/add/post")
    public String addArticlePost(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "announce") String announce,
                                 @RequestParam(name = "fullText") String fullText,
                                 Model model) {
        var article = ArticleCreateEditDto.builder()
                .title(title)
                .announce(announce)
                .fullText(fullText)
                .date(Calendar.getInstance().getTime())
                .build();
        articleService.create(article);
        return "redirect:/article";
    }
}
