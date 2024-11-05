package roomescape.dto.request;

public record ReservationRegisterRequest(
        String date,
        String name,
        String time
) {
}
