package by.ahmed.springapp.http.controller;

import by.ahmed.springapp.dto.AuthorCreateEditDto;
import by.ahmed.springapp.service.AuthorService;
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
@SessionAttributes({"authorDto", "errors"})
@Slf4j
public class RegistrationController {

    private final AuthorService authorService;

//    @GetMapping("/registration")
//    public String registration() {
//        return "/registration";
//    }

    @PostMapping("/registration")
    public String registration(Model model, AuthorCreateEditDto createAuthorDto,
                               RedirectAttributes redirectAttributes) {
        try {
            var userDto = authorService.create(createAuthorDto);
            addAttributes(model, Map.of("authorDto", userDto));
            return "redirect:/login";
        } catch (ValidationException exception) {
            addAttributes(model, Map.of("errors", exception.getErrors()));
            redirectAtt(createAuthorDto, redirectAttributes);
            return "redirect:/registration";
        }
    }

    private void redirectAtt(AuthorCreateEditDto createAuthorDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("firstname", createAuthorDto.getFirstname());
        redirectAttributes.addFlashAttribute("lastname", createAuthorDto.getLastname());
        redirectAttributes.addFlashAttribute("birthDate", createAuthorDto.getBirthDate());
        redirectAttributes.addFlashAttribute("gender", createAuthorDto.getGender());
        redirectAttributes.addFlashAttribute("jobTitle", createAuthorDto.getJobTitle());
        redirectAttributes.addFlashAttribute("email", createAuthorDto.getAuthentication().getEmail());
        redirectAttributes.addFlashAttribute("password", createAuthorDto.getAuthentication().getPassword());
    }
}
