package org.example.repository.impl;

import org.example.exception.DataBaseException;
import org.example.model.User;
import org.example.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean exist(String id) {
        return Database.getInstance().getUsers().containsKey(id);
    }

    @Override
    public void insert(User user) throws DataBaseException {
        if (user == null)
            throw new DataBaseException("User shouldn't be null");
        if (user.getId() == null)
            throw new DataBaseException("User Id shouldn't be null");
        if (user.getEmail() == null)
            throw new DataBaseException("User email shouldn't be null");

        Database.getInstance().getUsers().putIfAbsent(user.getId(), user);
    }

    @Override
    public void clean() {
        Database.getInstance().getUsers().clear();
    }
}
