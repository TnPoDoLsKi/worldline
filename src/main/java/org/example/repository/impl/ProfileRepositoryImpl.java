package org.example.repository.impl;

import org.example.exception.DataBaseException;
import org.example.model.Profile;
import org.example.repository.ProfileRepository;

public class ProfileRepositoryImpl implements ProfileRepository {

    @Override
    public boolean exist(String id) {
        return Database.getInstance().getProfiles().containsKey(id);
    }

    @Override
    public void insert(Profile profile) throws DataBaseException {
        if (profile == null) throw new DataBaseException("Profile shouldn't be null");
        if (profile.getId() == null) throw new DataBaseException("Profile Id shouldn't be null");
        if (!Database.getInstance().getUsers().containsKey(profile.getUserId()))
            throw new DataBaseException("Constraint violation user");

        Database.getInstance().getProfiles().putIfAbsent(profile.getUserId(), profile);
    }

    @Override
    public void clean() {
        Database.getInstance().getProfiles().clear();
    }
}
