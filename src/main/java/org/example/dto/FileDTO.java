package org.example.dto;

public class FileDTO {
    private String filename;
    private String mediaType;
    private byte[] content;

    public FileDTO() {
    }

    public FileDTO(String filename, String mediaType, byte[] content) {
        this.filename = filename;
        this.mediaType = mediaType;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
