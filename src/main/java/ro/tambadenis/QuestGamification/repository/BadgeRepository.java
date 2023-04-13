package ro.tambadenis.QuestGamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tambadenis.QuestGamification.model.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}

