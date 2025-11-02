package com.evdealer.evdealermanagement.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    private static final NumberFormat VND_FORMAT = NumberFormat.getInstance(new Locale("vi", "VN"));

    static {
        VND_FORMAT.setMaximumFractionDigits(0);
    }

    public static String format(Object amount) {
        if (amount == null) return "0 VND";

        BigDecimal value;
        if (amount instanceof Number) {
            value = new BigDecimal(((Number) amount).doubleValue());
        } else if (amount instanceof String) {
            try {
                value = new BigDecimal((String) amount);
            } catch (NumberFormatException e) {
                return "0 VND";
            }
        } else {
            return "0 VND";
        }

        return VND_FORMAT.format(value) + " VND";
    }
}
