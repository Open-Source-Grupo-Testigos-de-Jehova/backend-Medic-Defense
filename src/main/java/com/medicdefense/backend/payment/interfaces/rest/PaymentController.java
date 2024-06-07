package com.medicdefense.backend.payment.interfaces.rest;

import com.medicdefense.backend.payment.application.internal.commandservices.PaymentCommandServiceImpl;
import com.medicdefense.backend.payment.application.internal.queryservices.PaymentQueryServiceImpl;
import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.medicdefense.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.medicdefense.backend.payment.domain.services.PaymentCommandService;
import com.medicdefense.backend.payment.domain.services.PaymentQueryService;
import com.medicdefense.backend.payment.interfaces.rest.resources.CreatePaymentResource;
import com.medicdefense.backend.payment.interfaces.rest.resources.PaymentResource;
import com.medicdefense.backend.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.medicdefense.backend.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        var command = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Payment> payment = paymentCommandService.handle(command);
        return payment.map(value -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(value), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long id) {
        var query = new GetPaymentByIdQuery(id);
        Optional<Payment> payment = paymentQueryService.handle(query);
        return payment.map(value -> ResponseEntity.ok(PaymentResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<List<PaymentResource>> getPaymentsByConsultationId(@PathVariable Long consultationId) {
        var query = new GetAllPaymentsByConsultationIdQuery(consultationId);
        List<Payment> payments = paymentQueryService.handle(query);
        if (payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PaymentResource> paymentResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(paymentResources);
    }
}
