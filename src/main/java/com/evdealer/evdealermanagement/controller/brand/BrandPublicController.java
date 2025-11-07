package com.evdealer.evdealermanagement.controller.brand;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evdealer.evdealermanagement.dto.brand.BrandItemResponse;
import com.evdealer.evdealermanagement.service.implement.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public/brands")
@RequiredArgsConstructor
public class BrandPublicController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandItemResponse> listAll() {
        return brandService.listAllBrands();
    }
}
