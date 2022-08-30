package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.Document;

import java.util.Optional;

public interface DocumentRepository {
    void insert(Document document) throws DataBaseException;
    void clean() throws DataBaseException;
    Optional<Document> findByUserIdAndId(String userId, String id);
    boolean deleteByIdAndUserId(String id, String userId);
}
