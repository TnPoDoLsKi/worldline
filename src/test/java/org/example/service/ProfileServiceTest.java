package org.example.service;

import org.example.dto.ProfileDTO;
import org.example.exception.UnacceptedInputException;
import org.example.model.Profile;
import org.example.repository.ProfileRepository;
import org.example.repository.UserRepository;
import org.example.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileServiceTest {

    public UserRepository userRepository = Mockito.mock(UserRepository.class);
    public ProfileRepository profileRepository = Mockito.mock(ProfileRepository.class);
    public ProfileService profileService = new ProfileServiceImpl(profileRepository, userRepository);

    @Test
    void createProfileShouldWork() throws IOException {
        //Given
        ProfileDTO profileDTO = new ProfileDTO("Fredrik","Victor");
        String user = "1";
        Mockito.when(userRepository.exist(user)).thenReturn(true);
        Mockito.when(profileRepository.exist(user)).thenReturn(false);


        //When
        Optional<Profile> profile = profileService.create(profileDTO,user);

        //Then
        assert (profile.isPresent());
        assert (profile.get().getFirstname().equals(profileDTO.getFirstname()));
        assert (profile.get().getLastname().equals(profileDTO.getLastname()));
        assert (profile.get().getUserId().equals(user));

    }

    @Test
    void uploadDocumentShouldThrowException() throws IOException {
        //Given
        ProfileDTO profileDTO = new ProfileDTO("Fredrik",null);
        String user = "1";

        //Then(When)
        assertThrows(UnacceptedInputException.class, () -> profileService.create(profileDTO,user));
    }

}
