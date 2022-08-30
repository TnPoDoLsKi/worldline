package org.example.service.impl;

import org.example.dto.ProfileDTO;
import org.example.exception.UnacceptedInputException;
import org.example.model.Profile;
import org.example.repository.ProfileRepository;
import org.example.repository.UserRepository;
import org.example.service.ProfileService;

import java.util.Optional;
import java.util.UUID;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Optional<Profile> create(ProfileDTO profile, String userId) {
        if (profile == null) throw new UnacceptedInputException("Profile mustn't be null");
        if (userId == null) throw new UnacceptedInputException("User mustn't be null");
        if (!userRepository.exist(userId)) throw new UnacceptedInputException("User not found");
        if (profileRepository.exist(userId))
            throw new UnacceptedInputException("User already have a profile. Sorry we don't have the update yet");

        Profile profileModel = new Profile(UUID.randomUUID().toString(), profile.getFirstname(), profile.getLastname(), userId);
        profileRepository.insert(profileModel);
        return Optional.of(profileModel);
    }
}
