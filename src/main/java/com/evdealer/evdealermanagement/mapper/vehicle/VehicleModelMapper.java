package com.evdealer.evdealermanagement.mapper.vehicle;

import com.evdealer.evdealermanagement.dto.vehicle.update.UpdateVehicleModelResponse;
import com.evdealer.evdealermanagement.entity.vehicle.Model;

public class VehicleModelMapper {

    private VehicleModelMapper() {
        // Ngăn khởi tạo
    }

    public static UpdateVehicleModelResponse mapToUpdateVehicleModelResponse(Model model,
            boolean nameChanged,
            boolean categoryChanged) {
        if (model == null) {
            return null;
        }

        return UpdateVehicleModelResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .brandId(model.getBrand() != null ? model.getBrand().getId() : null)
                .vehicleTypeId(model.getVehicleType() != null ? model.getVehicleType().getId() : null)
                .nameChanged(nameChanged)
                .categoryChanged(categoryChanged)
                .build();
    }
}
