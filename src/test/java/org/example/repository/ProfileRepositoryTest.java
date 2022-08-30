package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.Profile;
import org.example.model.User;
import org.example.repository.impl.ProfileRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileRepositoryTest {

    private static ProfileRepository profileRepository;
    private static UserRepository userRepository;

    @BeforeAll()
    static void beforeAll() {
        profileRepository = new ProfileRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

    @AfterAll()
    static void afterAll() throws DataBaseException {
        profileRepository.clean();
        userRepository.clean();
    }

    @Test
    void insetProfileShouldWork() {
        //Given
        User user = new User(UUID.randomUUID().toString(), "fredrik@gmail.com");
        userRepository.insert(user);
        Profile expected = new Profile(UUID.randomUUID().toString(), "Nicolas", "Brown", user.getId());

        //When
        profileRepository.insert(expected);

        //Then
        assert (profileRepository.exist(user.getId()));
    }

    @Test
    void shouldThrowDataBaseException() {
        //Given
        Profile expected = new Profile(UUID.randomUUID().toString(), "Nicolas", "Brown", "Chradha");

        //Then(When)
        assertThrows(DataBaseException.class, () -> profileRepository.insert(expected));
    }
}
