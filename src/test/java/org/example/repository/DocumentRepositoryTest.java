package org.example.repository;

import org.example.exception.DataBaseException;
import org.example.model.Document;
import org.example.model.User;
import org.example.repository.impl.DocumentRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DocumentRepositoryTest {

    private static DocumentRepository documentRepository;
    private static UserRepository userRepository;

    @BeforeAll()
    static void beforeAll() {
        documentRepository = new DocumentRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

    @AfterAll()
    static void afterAll() throws DataBaseException {
        documentRepository.clean();
        userRepository.clean();
    }

    @Test
    void insetDocumentShouldWork() {
        //Given
        User user = new User(UUID.randomUUID().toString(), "fredrik@gmail.com");
        userRepository.insert(user);
        Document expected = new Document(UUID.randomUUID().toString(), user.getId(), "file.pdf", "application/pdf");

        //When
        documentRepository.insert(expected);

        //Then
        Optional<Document> document = documentRepository.findByUserIdAndId(user.getId(), expected.getId());
        assert (document.isPresent());
        assert (expected.equals(document.get()));
    }

    @Test
    void shouldThrowDataBaseException() {
        //Given
        Document expected = new Document(UUID.randomUUID().toString(), "test", "file.pdf", "application/pdf");

        //Then(When)
        assertThrows(DataBaseException.class, () -> documentRepository.insert(expected));
    }
}
