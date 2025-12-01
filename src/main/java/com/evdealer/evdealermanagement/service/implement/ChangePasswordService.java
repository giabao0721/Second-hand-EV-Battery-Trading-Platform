package com.evdealer.evdealermanagement.service.implement;

import com.evdealer.evdealermanagement.utils.VietNamDatetime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.evdealer.evdealermanagement.dto.account.password.PasswordResponse;
import com.evdealer.evdealermanagement.dto.account.password.ChangePasswordRequest;
import com.evdealer.evdealermanagement.entity.account.Account;
import com.evdealer.evdealermanagement.exceptions.AppException;
import com.evdealer.evdealermanagement.exceptions.ErrorCode;
import com.evdealer.evdealermanagement.repository.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChangePasswordService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PasswordResponse changePassword(String username, ChangePasswordRequest req) {
        log.info("=== [START] Change password for username: {} ===", username);

        // ===== 1) Kiểm tra xác nhận mật khẩu mới =====
        if (!req.getNewPassword().equals(req.getConfirmNewPassword())) {
            log.warn("Password confirmation mismatch for user: {}", username);
            return PasswordResponse.builder()
                    .success(false)
                    .message("New password and confirmation do not match.")
                    .build();
        }

        // ===== 2) Tìm tài khoản =====
        Account acc = accountRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found: {}", username);
                    return new AppException(ErrorCode.USER_NOT_FOUND, "User Not Found");
                });

        log.debug("Found user: id={}, username={}", acc.getId(), acc.getUsername());

        // ===== 3) Kiểm tra mật khẩu hiện tại =====
        if (!passwordEncoder.matches(req.getCurrentPassword(), acc.getPasswordHash())) {
            log.warn("Incorrect current password for user: {}", username);
            return PasswordResponse.builder()
                    .success(false)
                    .message("The current password is incorrect.")
                    .build();
        }

        // ===== 4) Kiểm tra trùng mật khẩu =====
        if (passwordEncoder.matches(req.getNewPassword(), acc.getPasswordHash())) {
            log.warn("New password is identical to current password for user: {}", username);
            return PasswordResponse.builder()
                    .success(false)
                    .message("The new password cannot be the same as the current password.")
                    .build();
        }

        // ===== 5) Cập nhật mật khẩu mới =====
        String newHash = passwordEncoder.encode(req.getNewPassword());
        acc.setPasswordHash(newHash);
        acc.setUpdatedAt(VietNamDatetime.nowVietNam());
        accountRepository.save(acc);

        log.info("Password updated successfully for username: {}", username);
        log.info("=== [END] Change password completed for user: {} ===", username);

        return PasswordResponse.builder()
                .success(true)
                .message("Password changed successfully. Please login again.")
                .build();
    }
}
