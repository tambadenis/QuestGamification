package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.Token;

import java.util.List;

public interface TokenService {
    List<Token> findAllTokens();
    Token findTokenById(Long id);
    Token saveToken(Token token);
    void deleteTokenById(Long id);
    Token generateToken();
    Token findByValue(String value);
    Token assignTokenToUser(String tokenValue, Long userId);
}

