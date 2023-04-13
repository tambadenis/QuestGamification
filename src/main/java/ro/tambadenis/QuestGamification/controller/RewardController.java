package ro.tambadenis.QuestGamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.tambadenis.QuestGamification.model.Reward;
import ro.tambadenis.QuestGamification.service.RewardService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public String getAllRewards(Model model) {
        List<Reward> rewards = rewardService.getAllRewards();
        model.addAttribute("rewards", rewards);
        return "rewards/list";
    }

    @GetMapping("/{id}")
    public String getRewardById(@PathVariable Long id, Model model) {
        Reward reward = rewardService.getRewardById(id);
        model.addAttribute("reward", reward);
        return "rewards/view";
    }

    @GetMapping("/new")
    public String showRewardForm(Model model) {
        model.addAttribute("reward", new Reward());
        return "rewards/form";
    }

    @PostMapping
    public String createReward(@Valid @ModelAttribute("reward") Reward reward, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rewards/form";
        }
        Reward createdReward = rewardService.createReward(reward);
        return "redirect:/api/rewards/" + createdReward.getId();
    }

    @GetMapping("/{id}/edit")
    public String showEditRewardForm(@PathVariable Long id, Model model) {
        Reward reward = rewardService.getRewardById(id);
        model.addAttribute("reward", reward);
        return "rewards/form";
    }

    @PutMapping("/{id}")
    public String updateReward(@PathVariable Long id, @Valid @ModelAttribute("reward") Reward reward,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rewards/form";
        }
        Reward updatedReward = rewardService.updateReward(id, reward);
        return "redirect:/api/rewards/" + updatedReward.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return "redirect:/api/rewards";
    }
}
