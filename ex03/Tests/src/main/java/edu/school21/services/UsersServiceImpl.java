package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.util.Objects;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) throws AlreadyAuthenticatedException, UsersRepository.EntityNotFoundException {
        User user = usersRepository.findByLogin(login);
        if (user.getStatus()) {
            throw new AlreadyAuthenticatedException("Authentication was performed");
        }
        if (user.getPassword().equals(password)) {
            user.setStatus(true);
            usersRepository.update(user);
            return true;
        }
        return false;
    }
}
