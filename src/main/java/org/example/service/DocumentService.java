package org.example.service;

import org.example.dto.FileDTO;
import org.example.model.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface DocumentService {
    Optional<Document> uploadDocument(String filename, String mediaType, InputStream inputStream, String userId);
    FileDTO retrieveDocument(String id, String userId) throws IOException;
    void delete(String id, String userId);
}
