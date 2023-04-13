package ro.tambadenis.QuestGamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.tambadenis.QuestGamification.model.Badge;
import ro.tambadenis.QuestGamification.service.BadgeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @GetMapping
    public String getAllBadges(Model model) {
        List<Badge> badges = badgeService.getAllBadges();
        model.addAttribute("badges", badges);
        return "badge-list";
    }

    @GetMapping("/{id}")
    public String getBadgeById(@PathVariable Long id, Model model) {
        Badge badge = badgeService.getBadgeById(id);
        model.addAttribute("badge", badge);
        return "badge-details";
    }

    @GetMapping("/new")
    public String createBadgeForm(Model model) {
        Badge badge = new Badge();
        model.addAttribute("badge", badge);
        return "badge-form";
    }

    @PostMapping
    public String createBadge(@Valid @ModelAttribute("badge") Badge badge) {
        Badge createdBadge = badgeService.createBadge(badge);
        return "redirect:/api/badges/" + createdBadge.getId();
    }

    @GetMapping("/{id}/edit")
    public String updateBadgeForm(@PathVariable Long id, Model model) {
        Badge badge = badgeService.getBadgeById(id);
        model.addAttribute("badge", badge);
        return "badge-form";
    }

    @PutMapping("/{id}")
    public String updateBadge(@PathVariable Long id, @Valid @ModelAttribute("badge") Badge badge) {
        Badge updatedBadge = badgeService.updateBadge(id, badge);
        return "redirect:/api/badges/" + updatedBadge.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteBadge(@PathVariable Long id) {
        badgeService.deleteBadge(id);
        return "redirect:/api/badges";
    }
}
