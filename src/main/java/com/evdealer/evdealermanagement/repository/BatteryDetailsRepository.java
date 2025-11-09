package com.evdealer.evdealermanagement.repository;

import com.evdealer.evdealermanagement.entity.battery.BatteryBrands;
import com.evdealer.evdealermanagement.entity.battery.BatteryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryDetailsRepository extends JpaRepository<BatteryDetails, String> {
    boolean existsByBrand_Id(String brandId);
}
