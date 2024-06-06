package com.medicdefense.backend.payment.interfaces.rest;

import com.medicdefense.backend.payment.application.internal.commandservices.PaymentCommandServiceImpl;
import com.medicdefense.backend.payment.domain.model.aggregates.Payment;
import com.medicdefense.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.medicdefense.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.medicdefense.backend.payment.interfaces.rest.resources.CreatePaymentResource;
import com.medicdefense.backend.payment.interfaces.rest.resources.PaymentResource;
import com.medicdefense.backend.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.medicdefense.backend.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentCommandServiceImpl paymentCommandService;
    private final PaymentQueryServiceImpl paymentQueryService;

    public PaymentController(PaymentCommandServiceImpl paymentCommandService, PaymentQueryServiceImpl paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }
    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        List<Payment> payments = paymentQueryService.handleGetAllPayments();
        List<PaymentResource> paymentResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResources);
    }
    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        CreatePaymentCommand command = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Payment> payment = paymentCommandService.handle(command);
        return payment.map(p -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(p), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable int id) {
        Optional<Payment> payment = paymentQueryService.handle(new GetPaymentByIdQuery(id));
        return payment.map(p -> ResponseEntity.ok(PaymentResourceFromEntityAssembler.toResourceFromEntity(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
