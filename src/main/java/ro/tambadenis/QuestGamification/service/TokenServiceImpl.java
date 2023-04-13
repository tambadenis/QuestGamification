package ro.tambadenis.QuestGamification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tambadenis.QuestGamification.exceptions.ResourceNotFoundException;
import ro.tambadenis.QuestGamification.model.Token;
import ro.tambadenis.QuestGamification.model.User;
import ro.tambadenis.QuestGamification.repository.TokenRepository;
import ro.tambadenis.QuestGamification.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Token> findAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token findTokenById(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Token", "id", id));
    }

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public void deleteTokenById(Long id) {
        tokenRepository.deleteById(id);
    }

    @Override
    public Token generateToken() {
        Token token = new Token();
        token.setValue(UUID.randomUUID().toString());
        return tokenRepository.save(token);
    }

    @Override
    public Token findByValue(String value) {
        return tokenRepository.findByValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Token", "value", value));
    }

    @Override
    public Token assignTokenToUser(String tokenValue, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Token token = findByValue(tokenValue);
        token.setUser(user);
        return tokenRepository.save(token);
    }
}
