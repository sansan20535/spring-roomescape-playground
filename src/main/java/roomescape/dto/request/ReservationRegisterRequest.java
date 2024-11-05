package roomescape.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRegisterRequest(
        @NotBlank(message = "날짜가 비어있습니다.")
        String date,
        @NotBlank(message = "이름이 비어있습니다.")
        String name,
        @NotBlank(message = "시간이 비어있습니다.")
        String time
) {
}
