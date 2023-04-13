package ro.tambadenis.QuestGamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tambadenis.QuestGamification.model.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
}

