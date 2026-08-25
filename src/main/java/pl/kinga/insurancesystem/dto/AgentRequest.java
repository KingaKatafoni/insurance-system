package pl.kinga.insurancesystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AgentRequest(
        @NotBlank(message = "First name cannot be empty")
        String firstName,

        @NotBlank(message = "Last name cannot be empty")
        String lastName,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email
) {
}
