package pl.kinga.insurancesystem.dto;

import java.time.LocalDate;
import java.util.List;

public record CustomerResponse(Long id, String fullName, String pesel, String email, LocalDate birthDate, String agentName, List<String> policyNumbers) {
}
