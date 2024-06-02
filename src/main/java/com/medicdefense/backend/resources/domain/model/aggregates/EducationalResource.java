package com.medicdefense.backend.resources.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EducationalResource extends AbstractAggregateRoot<EducationalResource> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String contentType; // e.g., "Article", "Video", "Book"

    @Column(nullable = false)
    private String url;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected EducationalResource() {
        // JPA requires a default constructor
    }

    /**
     * Constructor
     * It creates a new EducationalResource instance.
     * @param title - the title of the resource
     * @param author - the author of the resource
     * @param contentType - the type of the content (e.g., "Article", "Video", "Book")
     * @param url - the URL where the resource can be accessed
     */
    public EducationalResource(String title, String author, String contentType, String url) {
        this.title = title;
        this.author = author;
        this.contentType = contentType;
        this.url = url;
    }
}