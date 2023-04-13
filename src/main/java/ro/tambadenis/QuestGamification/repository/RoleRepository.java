package ro.tambadenis.QuestGamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tambadenis.QuestGamification.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

