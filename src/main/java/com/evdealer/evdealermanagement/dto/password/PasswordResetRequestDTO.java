package com.evdealer.evdealermanagement.dto.password;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetRequestDTO {
    @NotBlank
    // @Pattern()
    private String phone;
}
