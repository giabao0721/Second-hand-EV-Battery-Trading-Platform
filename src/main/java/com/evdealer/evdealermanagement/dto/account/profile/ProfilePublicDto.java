package com.evdealer.evdealermanagement.dto.account.profile;

import com.evdealer.evdealermanagement.entity.account.Account;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfilePublicDto {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String avatarUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate dateOfBirth;
    private String taxCode;
    private String nationalId;
    private Account.Gender gender;
    private Account.Status status;
    private Account.Role role;

}
