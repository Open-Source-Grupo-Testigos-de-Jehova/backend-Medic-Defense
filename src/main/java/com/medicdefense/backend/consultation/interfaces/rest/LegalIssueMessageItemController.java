package com.medicdefense.backend.consultation.interfaces.rest;

import com.medicdefense.backend.consultation.domain.model.commands.SendMessageCommand;
import com.medicdefense.backend.consultation.domain.model.queries.GetLastMessageItemByLegalIssueIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalIssueCommandService;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.interfaces.rest.resources.MessageItemResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.MessageItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/LegalIssue/{legalIssueId}/message-items", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Legal Issues")
public class LegalIssueMessageItemController {
    public final LegalIssueCommandService legalIssueCommandService;
    public final LegalIssueQueryService legalIssueQueryService;

    public LegalIssueMessageItemController(LegalIssueCommandService legalIssueCommandService, LegalIssueQueryService legalIssueQueryService) {
        this.legalIssueCommandService = legalIssueCommandService;
        this.legalIssueQueryService = legalIssueQueryService;
    }

    @PostMapping("{Message}")
    public ResponseEntity<MessageItemResource> sendMessageItemToLegalIssue(@PathVariable Long legalIssueId, @PathVariable String Message)
    {
        legalIssueCommandService.handle(new SendMessageCommand(legalIssueId, Message));
        var getLastMessageItemByLegalIssueIdQuery = new GetLastMessageItemByLegalIssueIdQuery(legalIssueId);
        var messageItem = legalIssueQueryService.handle(getLastMessageItemByLegalIssueIdQuery);
        if (messageItem.isEmpty()) return ResponseEntity.notFound().build();
        else {
            var messageItemResource = MessageItemResourceFromEntityAssembler.toResourceFromEntity(messageItem.get());
            return ResponseEntity.ok(messageItemResource);
        }
    }

    @GetMapping
    public ResponseEntity<MessageItemResource> getAllMessageItemsByLegalIssueId(@PathVariable Long legalIssueId) {
        var getLastMessageItemByLegalIssueIdQuery = new GetLastMessageItemByLegalIssueIdQuery(legalIssueId);
        var messageItem = legalIssueQueryService.handle(getLastMessageItemByLegalIssueIdQuery);
        if (messageItem.isEmpty()) return ResponseEntity.notFound().build();
        else {
            var messageItemResource = MessageItemResourceFromEntityAssembler.toResourceFromEntity(messageItem.get());
            return ResponseEntity.ok(messageItemResource);
        }
    }
}
