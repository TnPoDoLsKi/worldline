package org.example.service;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.example.dto.FileDTO;
import org.example.exception.UnacceptedInputException;
import org.example.model.Document;
import org.example.repository.DocumentRepository;
import org.example.repository.UserRepository;
import org.example.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DocumentServiceTest {
    public UserRepository userRepository = Mockito.mock(UserRepository.class);
    public DocumentRepository documentRepository = Mockito.mock(DocumentRepository.class);
    public DocumentService documentService = new DocumentServiceImpl(documentRepository, userRepository);


    @Test
    void uploadDocumentShouldWork() throws IOException {
        //Given
        String text = "test";
        String mediaType = "plain/text";
        String userId = "Fredrik";
        String filename = "test.txt";
        InputStream targetStream = new ByteArrayInputStream(text.getBytes());
        Mockito.when(userRepository.exist(userId)).thenReturn(true);

        //When
        Optional<Document> document = documentService.uploadDocument(filename, mediaType, targetStream, userId);

        //Then
        assert (document.get().getFilename().equals(filename));
        assert (document.get().getContentType().equals(mediaType));
        assert (document.get().getUserId().equals(userId));
    }

    @Test
    void uploadDocumentShouldThrowException() throws IOException {
        //Given
        String text = "test";
        String mediaType = "plain/text";
        String userId = "Fredrik";
        String filename = "test.txt";
        InputStream targetStream = null;
        Mockito.when(userRepository.exist(userId)).thenReturn(true);

        //Then(When)
        assertThrows(UnacceptedInputException.class, () -> documentService.uploadDocument(filename, mediaType, targetStream, userId));
    }


    @Test
    void retrieveDocumentShouldWork() throws IOException {
        //Given
        String text = "test";
        String mediaType = "plain/text";
        String userId = "Fredrik";
        String filename = "test.txt";
        InputStream targetStream = new ByteArrayInputStream(text.getBytes());
        Mockito.when(userRepository.exist(userId)).thenReturn(true);

        //When
        Optional<Document> document = documentService.uploadDocument(filename, mediaType, targetStream, userId);
        Mockito.when(documentRepository.findByUserIdAndId(document.get().getUserId(), document.get().getId())).thenReturn(document);
        FileDTO response = documentService.retrieveDocument(document.get().getId(), document.get().getUserId());

        //Then
        assert (new String(response.getContent()).equals(text));
        assert (response.getMediaType().equals(mediaType));
        assert (response.getFilename().equals(filename));
    }

    @Test
    void retrieveDocumentShouldThrowException() throws IOException {
        //Given
        String userId = "Fredrik";
        String documentId = "1";

        Mockito.when(documentRepository.findByUserIdAndId(userId, documentId)).thenReturn(Optional.empty());
        Mockito.when(userRepository.exist(userId)).thenReturn(true);

        //Then(When)
        assertThrows(UnacceptedInputException.class, () -> documentService.retrieveDocument(documentId, userId));
    }
}
