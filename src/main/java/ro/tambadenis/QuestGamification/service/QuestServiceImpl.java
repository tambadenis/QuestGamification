package ro.tambadenis.QuestGamification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tambadenis.QuestGamification.exceptions.ResourceNotFoundException;
import ro.tambadenis.QuestGamification.model.Quest;
import ro.tambadenis.QuestGamification.model.User;
import ro.tambadenis.QuestGamification.repository.QuestRepository;
import ro.tambadenis.QuestGamification.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestRepository questRepository;
    private UserRepository userRepository;

    @Override
    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    @Override
    public Quest getQuestById(Long id) {
        return questRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quest", "id", id));
    }

    @Override
    public Quest createQuest(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public Quest updateQuest(Long id, Quest quest) {
        Quest existingQuest = questRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quest", "id", id));
        existingQuest.setTitle(quest.getTitle());
        existingQuest.setDescription(quest.getDescription());
        existingQuest.setUser(quest.getUser());
        return questRepository.save(existingQuest);
    }

    @Override
    public void deleteQuest(Long id) {
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quest", "id", id));
        questRepository.delete(quest);
    }

    public void completeQuest(Long userId, Long questId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Quest quest = questRepository.findById(questId).orElseThrow(() -> new EntityNotFoundException("Quest not found"));

        if (!user.getQuests().contains(quest)) {
            throw new IllegalArgumentException("User is not assigned to the quest");
        }

        if (user.getQuests().stream().allMatch(Quest::isCompleted)) {
            throw new IllegalStateException("All quests are already completed");
        }

        if (quest.isCompleted()) {
            throw new IllegalStateException("Quest is already completed");
        }

        quest.setCompleted(true);
        user.addPoints(quest.getPoints());
        userRepository.save(user);
    }
}

