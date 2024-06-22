package com.medicdefense.backend.resources.domain.model.commands;

public record CreateEducationalResourceCommand(String title, String author, int contentType, String url) {
    public CreateEducationalResourceCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("author cannot be null or empty");
        }
        if (contentType < 1 || contentType > 9) {
            throw new IllegalArgumentException("contentType must be between 1 and 9");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("url cannot be null or empty");
        }
    }
}