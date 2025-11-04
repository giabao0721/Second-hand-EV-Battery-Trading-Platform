package com.evdealer.evdealermanagement.controller.transactions;

import com.evdealer.evdealermanagement.entity.transactions.PurchaseRequest;
import com.evdealer.evdealermanagement.repository.PurchaseRequestRepository;
import com.evdealer.evdealermanagement.service.implement.EversignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/webhooks/eversign")
@RequiredArgsConstructor
@Slf4j
public class EversignWebhookController {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final EversignService eversignService;

    /**
     * Webhook cho document completed (all signers signed)
     */
    @PostMapping("/document-complete")
    public ResponseEntity<?> handleDocumentComplete(
            @RequestBody(required = false) Map<String, Object> payload,
            @RequestHeader Map<String, String> headers
    ) {
        log.info("================== WEBHOOK RECEIVED ==================");
        log.info("📥 Headers: {}", headers);
        log.info("📦 Payload: {}", payload);
        log.info("=====================================================");

        if (payload == null || !payload.containsKey("document_hash")) {
            log.error("❌ Webhook nhận được body rỗng hoặc thiếu 'document_hash'");
            return ResponseEntity.ok(Map.of("success", true, "message", "Test webhook received"));
        }

        String documentHash = (String) payload.get("document_hash");
        log.info("🎯 Processing document_hash: {}", documentHash);

        try {
            eversignService.processDocumentCompletion(documentHash);
            log.info("✅ Webhook processed successfully for: {}", documentHash);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            log.error("❌ Lỗi khi xử lý webhook cho {}: {}", documentHash, e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}