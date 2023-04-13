package ro.tambadenis.QuestGamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.tambadenis.QuestGamification.model.Quest;
import ro.tambadenis.QuestGamification.service.QuestService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    @GetMapping
    public String getAllQuests(Model model) {
        List<Quest> quests = questService.getAllQuests();
        model.addAttribute("quests", quests);
        return "quest-list";
    }

    @GetMapping("/{id}")
    public String getQuestById(@PathVariable Long id, Model model) {
        Quest quest = questService.getQuestById(id);
        model.addAttribute("quest", quest);
        return "quest-details";
    }

    @GetMapping("/new")
    public String createQuestForm(Model model) {
        Quest quest = new Quest();
        model.addAttribute("quest", quest);
        return "quest-form";
    }

    @PostMapping
    public String createQuest(@Validated @ModelAttribute("quest") Quest quest) {
        Quest createdQuest = questService.createQuest(quest);
        return "redirect:/quests/" + createdQuest.getId();
    }

    @GetMapping("/{id}/edit")
    public String updateQuestForm(@PathVariable Long id, Model model) {
        Quest quest = questService.getQuestById(id);
        model.addAttribute("quest", quest);
        return "quest-form";
    }

    @PostMapping("/{id}")
    public String updateQuest(@PathVariable Long id, @Validated @ModelAttribute("quest") Quest quest) {
        Quest updatedQuest = questService.updateQuest(id, quest);
        return "redirect:/quests/" + updatedQuest.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteQuest(@PathVariable Long id) {
        questService.deleteQuest(id);
        return "redirect:/quests";
    }
}
