package com.medicdefense.backend.resources.domain.model.valueobjects;

public enum EducationalResourceContent {
    VIDEO(1),
    AUDIO(2),
    TEXT(3),
    IMAGE(4),
    PDF(5),
    SLIDES(6),
    EXERCISES(7),
    QUIZ(8),
    OTHER(9);

    private final int id;

    EducationalResourceContent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EducationalResourceContent fromId(int id) {
        for (EducationalResourceContent contentType : values()) {
            if (contentType.getId() == id) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}

