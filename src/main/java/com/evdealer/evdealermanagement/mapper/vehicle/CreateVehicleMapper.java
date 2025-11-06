package com.evdealer.evdealermanagement.mapper.vehicle;

import com.evdealer.evdealermanagement.dto.vehicle.create.CreateVehicleResponse;
import com.evdealer.evdealermanagement.entity.vehicle.Model;
import com.evdealer.evdealermanagement.entity.vehicle.ModelVersion;
import com.evdealer.evdealermanagement.entity.vehicle.VehicleBrands;

public class CreateVehicleMapper {

    private CreateVehicleMapper() {
        // Prevent instantiation
    }

    public static CreateVehicleResponse mapToCreateVehicleResponse(
            VehicleBrands brand,
            Model model,
            ModelVersion version,
            boolean brandCreated,
            boolean modelCreated,
            boolean versionCreated) {
        if (brand == null || model == null || version == null) {
            throw new IllegalArgumentException("Brand, Model, and Version must not be null");
        }

        CreateVehicleResponse resp = new CreateVehicleResponse();
        resp.setBrandId(brand.getId());
        resp.setModelId(model.getId());
        resp.setVersionId(version.getId());
        resp.setBrandCreated(brandCreated);
        resp.setModelCreated(modelCreated);
        resp.setVersionCreated(versionCreated);
        return resp;
    }
}
