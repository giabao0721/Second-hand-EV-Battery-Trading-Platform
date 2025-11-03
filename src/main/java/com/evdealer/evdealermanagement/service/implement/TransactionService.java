package com.evdealer.evdealermanagement.service.implement;

import com.evdealer.evdealermanagement.dto.payment.TransactionResponse;
import com.evdealer.evdealermanagement.entity.post.PostPackage;
import com.evdealer.evdealermanagement.entity.post.PostPackageOption;
import com.evdealer.evdealermanagement.entity.post.PostPayment;
import com.evdealer.evdealermanagement.entity.product.Product;
import com.evdealer.evdealermanagement.repository.PostPaymentRepository;
import com.evdealer.evdealermanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final PostPaymentRepository postPaymentRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<TransactionResponse> getAllTransactions(Pageable pageable) {
        Page<PostPayment> payments = postPaymentRepository.findAllByOrderByCreatedAtDesc(pageable);
        // Có thể dùng findAll(pageable) nếu đã sort trong pageable

        return payments.map(p -> {
            Product product = p.getProduct();
            PostPackage postPackage = p.getPostPackage();
            PostPackageOption postPackageOption = p.getPostPackageOption();

            Integer durationDays = null;
            if (postPackageOption != null && postPackageOption.getDurationDays() != null) {
                durationDays = postPackageOption.getDurationDays();
            } else if (postPackage != null) {
                durationDays = postPackage.getDurationDays();
            }

            return TransactionResponse.builder()
                    .paymentId(p.getId())
                    .createdAt(p.getCreatedAt())
                    .amount(p.getAmount())
                    .paymentMethod(p.getPaymentMethod() != null ? p.getPaymentMethod().name() : null)
                    .packageName(postPackage != null ? postPackage.getName() : null)
                    .durationDays(durationDays)
                    .productId(p.getProduct() != null ? p.getProduct().getId() : null)
                    .productName(product != null ? product.getTitle() : null)
                    .build();
        });
    }
}
