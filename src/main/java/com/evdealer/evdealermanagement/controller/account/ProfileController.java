package com.evdealer.evdealermanagement.controller.account;

import com.evdealer.evdealermanagement.dto.account.profile.AccountProfileResponse;
import com.evdealer.evdealermanagement.dto.account.profile.AccountUpdateRequest;
import com.evdealer.evdealermanagement.service.implement.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN') or hasRole('STAFF')")
    public AccountProfileResponse getCurrentProfile(Authentication authentication) {
        String username = authentication.getName();
        return profileService.getProfile(username);
    }



    @PatchMapping("/me/update")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<AccountProfileResponse> updateProfile(
            @RequestPart("data") @Valid AccountUpdateRequest request,
            Authentication authentication,
            @RequestPart("avatarUrl") MultipartFile avatarUrl) {
        String username = authentication.getName();
        return ResponseEntity.ok(profileService.updateProfile(username, request, avatarUrl));
    }

}
