package org.example.repository.impl;


import org.example.model.Document;
import org.example.model.Profile;
import org.example.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class Database {
    private final static Database instance = new Database();
    private final ConcurrentMap<String, User> users = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Profile> profiles = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Map<String, Document>> documents = new ConcurrentHashMap<>();

    private Database() {
        User test = new User();
        test.setId("testUser");
        users.put("testUser", test);
        User test1 = new User();
        test.setId("testUser1");
        users.put("testUser1", test);
    }

    public static Database getInstance() {
        return instance;
    }

    public ConcurrentMap<String, User> getUsers() {
        return users;
    }

    public ConcurrentMap<String, Profile> getProfiles() {
        return profiles;
    }

    public ConcurrentMap<String, Map<String, Document>> getDocuments() {
        return documents;
    }
}
