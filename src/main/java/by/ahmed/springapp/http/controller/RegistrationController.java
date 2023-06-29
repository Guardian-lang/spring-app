package by.ahmed.springapp.http.controller;

import by.ahmed.springapp.dto.UserCreateEditDto;
import by.ahmed.springapp.entity.Gender;
import by.ahmed.springapp.entity.Role;
import by.ahmed.springapp.service.UserService;
import by.ahmed.springapp.validator.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static by.ahmed.springapp.util.ModelHelper.addAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user", "genders", "roles", "errors"})
@Slf4j
public class RegistrationController {

    private final UserService userService;

//    @GetMapping("/registration")
//    public String registration() {
//        return "/registration";
//    }

    @PostMapping("/registration")
    public String registration(Model model, UserCreateEditDto createUserDto,
                               RedirectAttributes redirectAttributes) {
        try {
            var userDto = userService.create(createUserDto);
            addAttributes(model, Map.of("user", userDto));
            addAttributes(model, Map.of("genders", Gender.values()));
            addAttributes(model, Map.of("roles", Role.values()));
            return "redirect:/login";
        } catch (ValidationException exception) {
            addAttributes(model, Map.of("errors", exception.getErrors()));
            redirectAtt(createUserDto, redirectAttributes);
            return "redirect:/registration";
        }
    }

    private void redirectAtt(UserCreateEditDto createUserDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("first_name", createUserDto.getFirst_name());
        redirectAttributes.addFlashAttribute("last_name", createUserDto.getLast_name());
        redirectAttributes.addFlashAttribute("birth_date", createUserDto.getBirth_date());
        redirectAttributes.addFlashAttribute("gender", createUserDto.getGender());
        redirectAttributes.addFlashAttribute("role", createUserDto.getAuthentication().getRole());
        redirectAttributes.addFlashAttribute("email", createUserDto.getAuthentication().getEmail());
        redirectAttributes.addFlashAttribute("password", createUserDto.getAuthentication().getPassword());
    }
}
