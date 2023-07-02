package by.ahmed.springapp.http.controller;

import by.ahmed.springapp.dto.UserReadDto;
import by.ahmed.springapp.mapper.UserDtoConverter;
import by.ahmed.springapp.service.UserService;
import by.ahmed.springapp.util.ModelHelper;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static by.ahmed.springapp.util.ModelHelper.addAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes({"userDto", "errors"})
public class LoginController {

    private final UserService userService;

//    @GetMapping("/login")
//    public String loginPage() {
//        return "/login";
//    }

    @PostMapping("/login")
    public String validate(Model model, String email,
                           String password, RedirectAttributes redirectAttributes) {
        try {
            var user = userService.login(email, password);
            return user.map(userReadDto -> onLoginSuccess(model, userReadDto)).orElse(loginFail());
        } catch (ValidationException e) {
            addAttributes(model, Map.of("errors", e.getErrors()));
            ModelHelper.redirectAttributes(redirectAttributes, Map.of("email", email,
                    "password", password));
            return "redirect:/login";
        }
    }

    @GetMapping("login/fail")
    public String loginFail() {
        log.info("Wrong enter!");
        return "redirect:/login";
    }


    @SneakyThrows
    private String onLoginSuccess(Model model,
                                  UserReadDto user) {
        log.info("User logged: {}", user);
        addAttributes(model, Map.of("authorDto", user));
        return "redirect:/users/menu";
    }
}
