package com.evdealer.evdealermanagement.mapper.vehicle;

import com.evdealer.evdealermanagement.dto.vehicle.update.UpdateVehicleVersionResponse;
import com.evdealer.evdealermanagement.entity.vehicle.ModelVersion;

public class VehicleVersionMapper {

    private VehicleVersionMapper() {
        // Ngăn khởi tạo
    }

    public static UpdateVehicleVersionResponse mapToUpdateVehicleVersionResponse(ModelVersion ver,
            boolean nameChanged) {
        if (ver == null) {
            return null;
        }

        return UpdateVehicleVersionResponse.builder()
                .id(ver.getId())
                .name(ver.getName())
                .modelId(ver.getModel() != null ? ver.getModel().getId() : null)
                .nameChanged(nameChanged)
                .build();
    }
}
