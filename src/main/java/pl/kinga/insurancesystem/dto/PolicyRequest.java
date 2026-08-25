package pl.kinga.insurancesystem.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.kinga.insurancesystem.model.PolicyType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PolicyRequest(

        @NotBlank(message = "Numer polisy nie moze byc pusty")
        String policyNumber,

        @NotNull(message = "Typ nie moze byc pusty")
        PolicyType type,

        @NotBlank(message = "Imie i nazwisko ubezpieczonego nie moze byc puste")
        String holderName,

        @NotNull
        BigDecimal premiumAmount,

        @NotNull
        LocalDate startDate,

        @NotNull
        @Future
        LocalDate endDate

) {
}
