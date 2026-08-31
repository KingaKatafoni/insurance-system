package pl.kinga.insurancesystem.dto;

import java.util.List;

public record AgentResponse(Long id, String fullName, String email, List<String> policyNumbers, String phoneNumber) {
}
