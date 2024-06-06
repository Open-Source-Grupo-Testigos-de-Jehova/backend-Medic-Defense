package com.medicdefense.backend.resources.domain.model.aggregates;

import com.medicdefense.backend.resources.domain.model.commands.CreateEducationalResourceCommand;
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
    }

    /**
     * Constructor
     * It creates a new EducationalResource instance.
     * @param command - the CreateEducationalResourceCommand command
     */
    public EducationalResource(CreateEducationalResourceCommand command) {
        this.title = command.title();
        this.author = command.author();
        this.contentType = command.contentType();
        this.url = command.url();
    }
}
