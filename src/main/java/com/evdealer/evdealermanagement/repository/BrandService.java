package com.evdealer.evdealermanagement.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evdealer.evdealermanagement.dto.brand.BrandItemResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final VehicleBrandsRepository vehicleRepo;
    private final BatteryBrandsRepository batteryRepo;

    public List<BrandItemResponse> listAllBrands() {
        List<BrandItemResponse> result = new ArrayList<>();

        // Thêm brand của xe
        result.addAll(
                vehicleRepo.findAllByOrderByNameAsc()
                        .stream()
                        .map(b -> BrandItemResponse.builder()
                                .id(b.getId())
                                .name(b.getName())
                                .logoUrl(b.getLogoUrl())
                                .type("VEHICLE")
                                .build())
                        .toList());

        // Thêm brand của pin
        result.addAll(
                batteryRepo.findAllByOrderByNameAsc()
                        .stream()
                        .map(b -> BrandItemResponse.builder()
                                .id(b.getId())
                                .name(b.getName())
                                .logoUrl(b.getLogoUrl())
                                .type("BATTERY")
                                .build())
                        .toList());

        // Sắp xếp theo tên
        result.sort(Comparator.comparing(BrandItemResponse::getName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }
}