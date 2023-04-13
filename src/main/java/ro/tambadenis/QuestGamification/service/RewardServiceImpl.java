package ro.tambadenis.QuestGamification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tambadenis.QuestGamification.exceptions.ResourceNotFoundException;
import ro.tambadenis.QuestGamification.model.Reward;
import ro.tambadenis.QuestGamification.model.User;
import ro.tambadenis.QuestGamification.repository.RewardRepository;
import ro.tambadenis.QuestGamification.repository.UserRepository;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;
    private UserRepository userRepository;

    @Override
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Override
    public Reward getRewardById(Long id) {
        return rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward", "id", id));
    }

    @Override
    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Override
    public Reward updateReward(Long id, Reward reward) {
        Reward existingReward = rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward", "id", id));
        existingReward.setName(reward.getName());
        existingReward.setUser(reward.getUser());
        existingReward.setValue(reward.getValue());
        return rewardRepository.save(existingReward);
    }

    @Override
    public void deleteReward(Long id) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward", "id", id));
        rewardRepository.delete(reward);
    }
}
