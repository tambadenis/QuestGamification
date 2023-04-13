package ro.tambadenis.QuestGamification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tambadenis.QuestGamification.model.Token;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAll();
    Optional<Token> findById(Long id);
    Optional<Token> findByValue(String value);
    Token save(Token token);
    void deleteById(Long id);
}
