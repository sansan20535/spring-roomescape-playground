package roomescape.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRegisterRequest(
        @NotBlank
        String date,
        @NotBlank
        String name,
        @NotBlank
        String time
) {
}
