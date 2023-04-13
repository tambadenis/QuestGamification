package ro.tambadenis.QuestGamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tambadenis.QuestGamification.model.Quest;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {}

