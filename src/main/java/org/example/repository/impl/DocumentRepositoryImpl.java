package org.example.repository.impl;

import org.example.exception.DataBaseException;
import org.example.model.Document;
import org.example.repository.DocumentRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentRepositoryImpl implements DocumentRepository {


    public void insert(Document document) throws DataBaseException {
        if (document == null)
            throw new DataBaseException("PlayerLevelScore shouldn't be null");
        if (!Database.getInstance().getUsers().containsKey(document.getUserId()))
            throw new DataBaseException("Constraint violation for userId");

        Map<String, Document> mapLevel = new ConcurrentHashMap<>();
        mapLevel.put(document.getId(), document);
        Map<String, Document> userReference = Database.getInstance().getDocuments().putIfAbsent(document.getUserId(), mapLevel);
        if (userReference != null)
            Database.getInstance().getDocuments().computeIfPresent(document.getUserId(), (k, v) -> {
                v.put(document.getId(), document);
                return v;
            });
    }

    public Optional<Document> findByUserIdAndId(String userId, String id) {
        var a = Database.getInstance().getDocuments().get(userId);
        return Optional.ofNullable(a == null ? null : a.get(id));
    }

    public boolean deleteByIdAndUserId(String id, String userId) {
        return Database.getInstance().getDocuments().computeIfPresent(userId, (k, v) -> {
            v.remove(id);
            return v;
        }) != null;
    }

    @Override
    public void clean() {
        Database.getInstance().getDocuments().clear();
    }
}
