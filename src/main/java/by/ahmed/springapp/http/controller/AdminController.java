package by.ahmed.springapp.http.controller;

import by.ahmed.springapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@SessionAttributes({"user"})
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll(0, 12));
        return "users";
    }

//    @GetMapping("/users/{userId}/delete")
//    public String deleteUser(@PathVariable("userId") Long id,
//            Model model) {
//
//    }
}
