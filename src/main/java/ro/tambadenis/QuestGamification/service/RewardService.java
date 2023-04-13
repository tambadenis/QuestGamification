package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> getAllRewards();
    Reward getRewardById(Long id);
    Reward createReward(Reward reward);
    Reward updateReward(Long id, Reward reward);
    void deleteReward(Long id);
}
