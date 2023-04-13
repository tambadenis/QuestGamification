package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.Badge;

import java.util.List;

public interface BadgeService {
    List<Badge> getAllBadges();
    Badge getBadgeById(Long id);
    Badge createBadge(Badge badge);
    Badge updateBadge(Long id, Badge badge);
    void deleteBadge(Long id);
}

