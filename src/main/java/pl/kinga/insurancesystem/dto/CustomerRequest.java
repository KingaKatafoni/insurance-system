package pl.kinga.insurancesystem.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CustomerRequest(
        @NotBlank(message = "First name is mandatory")
        String firstName,

        @NotBlank(message = "First name is mandatory")
        String lastName,

        @NotBlank(message = "Pesel is mandatory")
        @Size(min = 11, max = 11)
        String pesel,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Birth date is mandatory")
        @Past
        LocalDate birthDate

        ) {
}
