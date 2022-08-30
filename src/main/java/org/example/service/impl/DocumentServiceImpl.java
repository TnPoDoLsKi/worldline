package org.example.service.impl;

import org.example.dto.FileDTO;
import org.example.exception.DataBaseException;
import org.example.exception.UnacceptedInputException;
import org.example.model.Document;
import org.example.repository.DocumentRepository;
import org.example.repository.UserRepository;
import org.example.service.DocumentService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public Optional<Document> uploadDocument(String filename, String mediaType, InputStream inputStream, String userId) {

        if (userId == null || !userRepository.exist(userId))
            throw new UnacceptedInputException("User not found");
        if (inputStream == null)
            throw new UnacceptedInputException("Stream shouldn't be null");
        if (filename == null)
            throw new UnacceptedInputException("filename shouldn't be null");
        if (mediaType == null)
            throw new UnacceptedInputException("mediaType shouldn't be null");

        Document document = new Document(UUID.randomUUID().toString(), userId, filename, mediaType);

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream out = Files.newOutputStream(java.nio.file.Path.of(document.getId() + filename));
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            documentRepository.insert(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnacceptedInputException("Error while uploading file. Please try again !!");
        }
        return Optional.of(document);
    }

    @Override
    public FileDTO retrieveDocument(String id, String userId) throws IOException {
        if (userId == null || !userRepository.exist(userId))
            throw new UnacceptedInputException("User not found");
        Optional<Document> document = documentRepository.findByUserIdAndId(userId, id);
        if (document.isEmpty())
            throw new UnacceptedInputException("Document id not found");
        try {
            File file = new File(document.get().getId() + document.get().getFilename());
            InputStream inputStream = new FileInputStream(file);
            return new FileDTO(document.get().getFilename(),document.get().getContentType(),inputStream.readAllBytes());
        } catch (Exception e) {
            throw new DataBaseException("Unexpected server error");
        }
    }

    @Override
    public void delete(String id, String userId) {
        if (userId == null || !userRepository.exist(userId))
            throw new UnacceptedInputException("User not found");
        if (!documentRepository.deleteByIdAndUserId(id, userId))
            throw new UnacceptedInputException("Document id not found");
    }
}
