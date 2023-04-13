package ro.tambadenis.QuestGamification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tambadenis.QuestGamification.exceptions.ResourceNotFoundException;
import ro.tambadenis.QuestGamification.model.Badge;
import ro.tambadenis.QuestGamification.repository.BadgeRepository;

import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    @Override
    public Badge getBadgeById(Long id) {
        return badgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Badge", "id", id));
    }

    @Override
    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    @Override
    public Badge updateBadge(Long id, Badge badge) {
        Badge existingBadge = badgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Badge", "id", id));
        existingBadge.setName(badge.getName());
        existingBadge.setDescription(badge.getDescription());
        existingBadge.setImageUrl(badge.getImageUrl());
        return badgeRepository.save(existingBadge);
    }

    @Override
    public void deleteBadge(Long id) {
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Badge", "id", id));
        badgeRepository.delete(badge);
    }
}

