package com.evdealer.evdealermanagement.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandItemResponse {
    private String id;
    private String name;
    private String logoUrl;
    private String type;
}