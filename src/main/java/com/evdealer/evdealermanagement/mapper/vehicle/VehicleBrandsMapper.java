package com.evdealer.evdealermanagement.mapper.vehicle;

import com.evdealer.evdealermanagement.dto.vehicle.brand.VehicleBrandsResponse;
import com.evdealer.evdealermanagement.entity.vehicle.VehicleBrands;

public class VehicleBrandsMapper {

    public static VehicleBrandsResponse mapToVehicleBrandsResponse(VehicleBrands entity) {
        if (entity == null) {
            return null;
        }

        return VehicleBrandsResponse.builder()
                .brandId(entity.getId())
                .brandName(entity.getName())
                .logoUrl(entity.getLogoUrl())
                .build();
    }
}
