package com.evdealer.evdealermanagement.mapper.staff;

import com.evdealer.evdealermanagement.dto.rate.ApprovalRateResponse;

public class ApprovalRateMapper {

    private ApprovalRateMapper() {
        // chặn khởi tạo
    }

    public static ApprovalRateResponse mapToApprovalRateResponse(long approved,
            long rejected,
            long decided,
            double rate,
            String rateText) {
        return ApprovalRateResponse.builder()
                .approved(approved)
                .rejected(rejected)
                .total(decided)
                .rate(rate)
                .rateText(rateText)
                .build();
    }
}
