package ro.tambadenis.QuestGamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.tambadenis.QuestGamification.model.User;
import ro.tambadenis.QuestGamification.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User()); // adăugăm un obiect User gol pentru formularul de adăugare user
        return "users/list"; // numele template-ului Thymeleaf
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/details"; // numele template-ului Thymeleaf
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form"; // numele template-ului Thymeleaf
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user) {
        userService.createUser(user);
        return "redirect:/api/users"; // redirecționează către pagina de listare a utilizatorilor
    }

    @GetMapping("/{id}/edit")
    public String updateUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/form"; // numele template-ului Thymeleaf
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") @Valid User user) {
        userService.updateUser(id, user);
        return "redirect:/api/users"; // redirecționează către pagina de listare a utilizatorilor
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/api/users"; // redirecționează către pagina de listare a utilizatorilor
    }
}
