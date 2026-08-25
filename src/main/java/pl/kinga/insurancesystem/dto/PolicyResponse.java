package pl.kinga.insurancesystem.dto;

import pl.kinga.insurancesystem.model.PolicyType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyResponse(Long id, String policyNumber, PolicyType type, String holderName, BigDecimal premiumAmount,
                             LocalDate startDate, LocalDate endDate) {
}
