package com.medicdefense.backend.resources.domain.model.commands;

public record CreateEducationalResourceCommand(String title, String author, String contentType, String url) {
    public CreateEducationalResourceCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("author cannot be null or empty");
        }
        if (contentType == null || contentType.isBlank()) {
            throw new IllegalArgumentException("contentType cannot be null or empty");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("url cannot be null or empty");
        }
    }
}