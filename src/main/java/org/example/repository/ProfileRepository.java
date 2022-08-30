package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.Profile;


public interface ProfileRepository {
    boolean exist(String id);
    void insert(Profile profile) throws DataBaseException;
    void clean() throws DataBaseException;

}
