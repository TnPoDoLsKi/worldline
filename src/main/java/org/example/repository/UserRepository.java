package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.User;

public interface UserRepository {
    boolean exist(String id);
    void insert(User user) throws DataBaseException;
    void clean() throws DataBaseException;
}
