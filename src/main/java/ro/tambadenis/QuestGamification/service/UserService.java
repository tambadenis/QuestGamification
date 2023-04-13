package ro.tambadenis.QuestGamification.service;

import ro.tambadenis.QuestGamification.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> findAllUsersByRanking();
}
