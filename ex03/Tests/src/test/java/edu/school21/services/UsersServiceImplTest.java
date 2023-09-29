package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;

public class UsersServiceImplTest {
    @Mock
    UsersRepository mockRepository;

    public UsersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void authenticateTrue() {
        UsersServiceImpl mockService = new UsersServiceImpl(mockRepository);
        given(mockRepository.findByLogin("Lala")).willReturn(new User(1L, "Lala", "lala", false));
        Assertions.assertTrue(mockService.authenticate("Lala", "lala"));
    }

    @Test
    public void authenticateFalse() {
        UsersServiceImpl mockService = new UsersServiceImpl(mockRepository);
        given(mockRepository.findByLogin("Nana")).willReturn(new User(2L, "Nana", "Nana", false));
        Assertions.assertFalse(mockService.authenticate("Nana", "Mama"));
    }

    @Test
    public void authenticateEntityThrow() {
        UsersServiceImpl mockService = new UsersServiceImpl(mockRepository);
        given(mockRepository.findByLogin("Toto")).willThrow(UsersRepository.EntityNotFoundException.class);
        Assertions.assertThrows(UsersRepository.EntityNotFoundException.class, () -> mockService.authenticate("Toto", "toto"));
    }

    @Test
    public void authenticateAlreadyThrow() {
        UsersServiceImpl mockService = new UsersServiceImpl(mockRepository);
        given(mockRepository.findByLogin("Lala")).willReturn(new User(1L, "Lala", "Lala", true));
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> mockService.authenticate("Lala", "lala"));
    }
}
