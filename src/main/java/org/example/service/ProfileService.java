package org.example.service;

import org.example.dto.ProfileDTO;
import org.example.model.Profile;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> create(ProfileDTO profile, String userId);
}
