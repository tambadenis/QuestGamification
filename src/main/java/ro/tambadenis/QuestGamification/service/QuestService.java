package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.Quest;

import java.util.List;

public interface QuestService {
    List<Quest> getAllQuests();
    Quest getQuestById(Long id);
    Quest createQuest(Quest quest);
    Quest updateQuest(Long id, Quest quest);
    void deleteQuest(Long id);
}

