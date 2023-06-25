package by.ahmed.springapp.http.controller;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginRegistrationController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
        log.info("Author logged");
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("author") AuthorCreateEditDto author) {
        model.addAttribute("author", author);
        log.info("New author: {}", author);
        return "registration";
    }
}
