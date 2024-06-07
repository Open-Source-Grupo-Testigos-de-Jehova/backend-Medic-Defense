package com.medicdefense.backend.resources.interfaces.rest;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.queries.*;
import com.medicdefense.backend.resources.domain.model.queries.GetEducationalResourcesByContentTypeQuery;
import com.medicdefense.backend.resources.domain.services.EducationalResourceCommandService;
import com.medicdefense.backend.resources.domain.services.EducationalResourceQueryService;
import com.medicdefense.backend.resources.interfaces.rest.resources.CreateEducationalResourceResource;
import com.medicdefense.backend.resources.interfaces.rest.resources.EducationalResourceResource;
import com.medicdefense.backend.resources.interfaces.rest.transform.CreateEducationalResourceCommandFromResourceAssembler;
import com.medicdefense.backend.resources.interfaces.rest.transform.EducationalResourceResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/educational-resources")
public class EducationalResourceController {
    private final EducationalResourceCommandService educationalResourceCommandService;
    private final EducationalResourceQueryService educationalResourceQueryService;

    public EducationalResourceController(EducationalResourceCommandService educationalResourceCommandService,
                                         EducationalResourceQueryService educationalResourceQueryService) {
        this.educationalResourceCommandService = educationalResourceCommandService;
        this.educationalResourceQueryService = educationalResourceQueryService;
    }

    @PostMapping
    public ResponseEntity<EducationalResourceResource> createEducationalResource(
            @RequestBody CreateEducationalResourceResource resource) {
        Optional<EducationalResource> educationalResource = educationalResourceCommandService
                .handle(CreateEducationalResourceCommandFromResourceAssembler.toCommandFromResource(resource));
        return educationalResource.map(resourceCreated -> new ResponseEntity<>(
                        EducationalResourceResourceFromEntityAssembler.toResourceFromEntity(resourceCreated), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<EducationalResourceResource> getEducationalResourceById(@PathVariable Long id) {
        Optional<EducationalResource> educationalResource = educationalResourceQueryService
                .handle(new GetEducationalResourceByIdQuery(id));
        return educationalResource.map(resourceFound -> ResponseEntity.ok(EducationalResourceResourceFromEntityAssembler
                .toResourceFromEntity(resourceFound))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EducationalResourceResource>> getEducationalResources(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String author) {
        if (title != null) {
            return getAllEducationalResourcesByTitle(title);
        } else if (content != null) {
            return getAllEducationalResourcesByContent(content);
        } else if (author != null) {
            return getAllEducationalResourcesByAuthor(author);
        } else {
            return getAllEducationalResources();
        }
    }

    private ResponseEntity<List<EducationalResourceResource>> getAllEducationalResourcesByTitle(String title) {
        var query = new GetEducationalResourcesByTitleQuery(title);
        var educationalResources = educationalResourceQueryService.handle(query);
        if (educationalResources.isEmpty()) return ResponseEntity.notFound().build();
        var educationalResourceResources = educationalResources.stream().map(
                EducationalResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(educationalResourceResources);
    }

    private ResponseEntity<List<EducationalResourceResource>> getAllEducationalResourcesByContent(String content) {
        var query = new GetEducationalResourcesByContentTypeQuery(content);
        var educationalResources = educationalResourceQueryService.handle(query);
        if (educationalResources.isEmpty()) return ResponseEntity.notFound().build();
        var educationalResourceResources = educationalResources.stream().map(
                EducationalResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(educationalResourceResources);
    }

    private ResponseEntity<List<EducationalResourceResource>> getAllEducationalResourcesByAuthor(String author) {
        var query = new GetEducationalResourcesByAuthorQuery(author);
        var educationalResources = educationalResourceQueryService.handle(query);
        if (educationalResources.isEmpty()) return ResponseEntity.notFound().build();
        var educationalResourceResources = educationalResources.stream().map(
                EducationalResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(educationalResourceResources);
    }

    private ResponseEntity<List<EducationalResourceResource>> getAllEducationalResources() {
        var educationalResources = educationalResourceQueryService.handle(new GetAllEducationalResourcesQuery());
        var educationalResourceResources = educationalResources.stream().map(
                EducationalResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(educationalResourceResources);
    }
}
