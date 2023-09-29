package edu.school21.repositories;

import edu.school21.models.User;

public interface UsersRepository {
    public class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super("A user that does not exist in the database");
        }
    }
    User findByLogin(String login);
    void update(User user);
}
