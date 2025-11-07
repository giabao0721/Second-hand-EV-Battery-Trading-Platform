package com.evdealer.evdealermanagement.dto.product.show;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.evdealer.evdealermanagement.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    String id;
    String title;
    String description;

    Product.ProductType type; // BATTERY | VEHICLE
    BigDecimal price; // DECIMAL(15,2)
    Short manufactureYear; // SMALLINT
    Product.ConditionType conditionType; // NEW | USED

    // seller là Account -> trả về sellerId để khớp cột CHAR(36)
    String sellerId;

    Product.Status status; // DRAFT | ACTIVE | ...
    LocalDateTime createdAt; // TIMESTAMP
    LocalDateTime updatedAt; // DATETIME(6)

    String addressDetail; // TEXT
    String city;
    String district;
    String ward;

    String sellerPhone;

    Product.SaleType saleType; // AUCTION | FIXED_PRICE | NEGOTIATION
    BigDecimal postingFee; // DECIMAL(8,2)
    String rejectReason;

    LocalDateTime expiresAt; // DATETIME(6)
    LocalDateTime featuredEndAt; // DATETIME(6)
    LocalDateTime startRenewalAt; // DATETIME(6)

    // approvedBy là Account -> trả về id
    String approvedBy;

    Boolean isHot; // TINYINT(1)
    Boolean remindBefore2Sent; // TINYINT(1)
}
