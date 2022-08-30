package org.example.model;

import java.util.Objects;

public class Document {
    private String id;
    private String userId;
    private String filename;
    private String contentType;

    public Document(String id, String userId, String filename, String contentType) {
        this.id = id;
        this.userId = userId;
        this.filename = filename;
        this.contentType = contentType;
    }

    public Document() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id) && Objects.equals(userId, document.userId) && Objects.equals(filename, document.filename) && Objects.equals(contentType, document.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, filename, contentType);
    }
}
