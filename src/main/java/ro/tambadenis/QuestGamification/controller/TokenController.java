package ro.tambadenis.QuestGamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.tambadenis.QuestGamification.model.Token;
import ro.tambadenis.QuestGamification.service.TokenService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public String getAllTokens(Model model) {
        List<Token> tokens = tokenService.findAllTokens();
        model.addAttribute("tokens", tokens);
        return "tokens/list";
    }

    @GetMapping("/{id}")
    public String getTokenById(@PathVariable Long id, Model model) {
        Token token = tokenService.findTokenById(id);
        model.addAttribute("token", token);
        return "tokens/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("token", new Token());
        return "tokens/form";
    }

    @PostMapping("/new")
    public String createToken(@Valid @ModelAttribute("token") Token token, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tokens/form";
        }
        tokenService.saveToken(token);
        return "redirect:/tokens";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Token token = tokenService.findTokenById(id);
        model.addAttribute("token", token);
        return "tokens/form";
    }

    @PostMapping("/{id}/edit")
    public String updateToken(@PathVariable Long id, @Valid @ModelAttribute("token") Token tokenDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tokens/form";
        }
        Token tokenToUpdate = tokenService.findTokenById(id);
        tokenToUpdate.setValue(tokenDetails.getValue());
        tokenService.saveToken(tokenToUpdate);
        return "redirect:/tokens";
    }

    @PostMapping("/{id}/delete")
    public String deleteToken(@PathVariable Long id) {
        tokenService.deleteTokenById(id);
        return "redirect:/tokens";
    }
}
