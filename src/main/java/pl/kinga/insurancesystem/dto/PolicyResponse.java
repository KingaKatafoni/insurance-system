package pl.kinga.insurancesystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyResponse(Long id, String policyNumber, String type, String holderName, BigDecimal premiumAmount,
                             LocalDate startDate, LocalDate endDate) {
}
