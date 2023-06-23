package by.ahmed.springapp.greeting.controller;

import by.ahmed.springapp.dto.ArticleCreateEditDto;
import by.ahmed.springapp.entity.Article;
import by.ahmed.springapp.mapper.ArticleMapper;
import by.ahmed.springapp.repository.ArticleRepository;
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

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @GetMapping("/article")
    public String mainArticle(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "article";
    }

    @GetMapping("/article/add")
    public String addArticle(Model model) {
        return "add-article";
    }

    @PostMapping("/article/add")
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
        var result = articleMapper.toArticle(article);
        articleRepository.save(result);
        return "redirect:/article";
    }
}
