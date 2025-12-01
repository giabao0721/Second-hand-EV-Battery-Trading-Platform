package com.evdealer.evdealermanagement.controller.member;

import com.evdealer.evdealermanagement.dto.product.detail.ProductDetail;
import com.evdealer.evdealermanagement.service.implement.RecentViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/recent")
@RequiredArgsConstructor
public class RecentViewController {

    private final RecentViewService recentViewService;

    /**
     * 📌 Ghi lại sản phẩm vừa xem
     */
    @PostMapping("/{productId}")
    public ResponseEntity<Void> addRecent(@PathVariable String productId) {
        recentViewService.addRecentView(productId);
        return ResponseEntity.ok().build();
    }

    /**
     * 📌 Lấy danh sách sản phẩm đã xem gần đây (có hỗ trợ phân trang)
     * - Nếu `paged=false` → trả về tối đa 10 sản phẩm gần nhất
     * - Nếu `paged=true` → trả về dữ liệu Page<ProductDetail>
     */
    @GetMapping
    public ResponseEntity<?> getUserRecentView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "false") boolean paged
    ) {
        if (paged) {
            Page<ProductDetail> result = recentViewService.getRecentViewedProductsPaged(page, size);
            return ResponseEntity.ok(result);
        } else {
            List<ProductDetail> result = recentViewService.getRecentViewedProducts();
            return ResponseEntity.ok(result);
        }
    }
}
