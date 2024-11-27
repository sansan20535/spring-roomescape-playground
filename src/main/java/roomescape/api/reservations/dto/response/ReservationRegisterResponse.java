package roomescape.api.reservations.dto.response;

public record ReservationRegisterResponse(
        long id,
        String name,
        String date,
        String time
) {

    public static ReservationRegisterResponse of(final long id, final String name, final String date, final String time) {
        return new ReservationRegisterResponse(id, name, date, time);
    }
}
