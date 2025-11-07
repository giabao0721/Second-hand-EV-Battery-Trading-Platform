package com.evdealer.evdealermanagement.controller.admin;

import com.evdealer.evdealermanagement.dto.brand.BrandItemResponse;
import com.evdealer.evdealermanagement.service.implement.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/brands")
@RequiredArgsConstructor
public class AdminBrandsManagementController {

    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<BrandItemResponse> createBrand(
            @RequestBody String name,
            @RequestBody String logoUrl,
            @RequestBody String type) {
        return ResponseEntity.ok(brandService.createBrand(name, logoUrl, type));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BrandItemResponse> updateBrand(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {

        String name = (String) updates.get("name");
        String logoUrl = (String) updates.get("logoUrl");
        String type = (String) updates.get("type");

        return ResponseEntity.ok(
                brandService.updateBrand(id, name, logoUrl, type)
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBrand(
            @PathVariable String id,
            @RequestParam String type) {

        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = brandService.deleteBrand(id, type);

            if (deleted) {
                response.put("success", true);
                response.put("message", "Xóa brand thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Không tìm thấy brand với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi xóa brand: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
