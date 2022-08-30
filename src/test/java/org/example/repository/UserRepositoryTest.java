package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.User;
import org.example.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserRepositoryTest {

    private static UserRepository userRepository;

    @BeforeAll()
    static void beforeAll() {
        userRepository = new UserRepositoryImpl();
    }

    @AfterAll()
    static void afterAll() throws DataBaseException {
        userRepository.clean();
    }

    @Test
    void insetUserShouldWork() {
        //Given
        User user = new User(UUID.randomUUID().toString(), "fredrik@gmail.com");

        //When
        userRepository.insert(user);

        //Then
        assert (userRepository.exist(user.getId()));
    }

}
